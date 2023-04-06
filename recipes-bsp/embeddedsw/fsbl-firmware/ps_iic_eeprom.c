#include "xfsbl_hw.h"
#include "xiicps.h"

#include "ps_iic_eeprom.h"

int psu_read_eeprom(u16 device_id, u16 i2c_slave_addr, u16 offset, u8 offset_size, u8 *data, u8 size)
{
	XIicPs IicInstance;
	XIicPs_Config *ConfigIic;
	s32 Status;
	u32 Regval;
	u8 TxArray[2];

	/* Lookup for I2C-1U device */
	ConfigIic = XIicPs_LookupConfig(device_id);
	if (!ConfigIic)
		return 1;

	/* Initialize the I2C device */
	Status = XIicPs_CfgInitialize(&IicInstance, ConfigIic,
			ConfigIic->BaseAddress);
	if (Status != XST_SUCCESS)
		return Status;

	/* Set the Serial Clock for I2C */
	Status = XIicPs_SetSClk(&IicInstance, 400000);
	if (Status != XST_SUCCESS)
		return Status;

	/* Wait until bus is idle  */
	Regval = 0;
	Status = XFsbl_PollTimeout(IicInstance.Config.BaseAddress +
			XIICPS_SR_OFFSET, Regval, (Regval &	XIICPS_SR_BA_MASK) == 0x0U,
			100000u);
	if (Status != XST_SUCCESS)
		return Status;

	/* Set the hold bit on the write transfer */
	XIicPs_SetOptions(&IicInstance, XIICPS_REP_START_OPTION);

	/* Send address byte(s), MSB first */
	TxArray[0] = offset >> 8;
	TxArray[1] = offset;
	XIicPs_MasterSendPolled(&IicInstance, TxArray + 2 - offset_size, offset_size, i2c_slave_addr);

	/* Clear hold bit after next transfer */
	XIicPs_ClearOptions(&IicInstance, XIICPS_REP_START_OPTION);

	/* Receive the Data from EEPROM via I2C. */
	Status = XIicPs_MasterRecvPolled(&IicInstance, data, size, i2c_slave_addr);
	if (Status != XST_SUCCESS)
		return Status;

	return 0;
}


#if 0
/* Example usage: */
	u8 eeprom_data[4];

	if (psu_read_eeprom(1 /* XPAR_PSU_I2C_1_DEVICE_ID */,
		0x51 /* EEPROM_I2C_ADDR */,
		0xe0 /* EEPROM_I2C_OFFSET */,
		1 /* single-byte address */,
		eeprom_data, sizeof(eeprom_data)) == 0)
	{
		// Do something useful with the bytes //
	}
#endif
