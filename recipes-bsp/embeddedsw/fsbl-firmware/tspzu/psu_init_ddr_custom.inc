#include "ps_iic_eeprom.h"
#include "psu_init_ddr.inc"

/* I2C bus or controller index, can be 0 or 1 */
#define BOARD_DDR_EEPROM_I2C_BUS    1
/* I2C EEPROM chip address, usually between 0x50 and 0x57 */
#define BOARD_DDR_EEPROM_I2C_ADDR   0x51
/* Offset inside EEPROM of 4-byte DDR parameter data block */
#define BOARD_DDR_EEPROM_I2C_OFFSET 0xF68
/* EEPROM chip type, 1 for 1 byte addressing, 2 for 2-byte addressing. Must
 * match the hardware, otherwise it may overwrite data in the chip. */
#define BOARD_DDR_EEPROM_I2C_MODE   2

static void psu_init_ddr_eeprom(int phy_bringup)
{
	u8 eeprom_data[4];
	u8 ecc_mode = 0;
	u8 bus_width = 64; /* 32 or 64, dangerous default? */
	u8 ddr_size = 8; /* Chip density, 8 or 16 */

	if (psu_read_eeprom(BOARD_DDR_EEPROM_I2C_BUS,
		BOARD_DDR_EEPROM_I2C_ADDR,
		BOARD_DDR_EEPROM_I2C_OFFSET,
		BOARD_DDR_EEPROM_I2C_MODE,
		eeprom_data, sizeof(eeprom_data)) == 0)
	{
		/* Read okay, try to obtain the parameters from these bytes */
		/* ECC: "1" means ECC installed, anything else = no ECC */
		switch (eeprom_data[3]) {
		case 0x01:
			ecc_mode = 1;
			break;
		default:
			ecc_mode = 0;
			break;
		}
		/* Bus width divided by 16 (number of chips) */
		switch (eeprom_data[2]) {
		case 0x02:
			bus_width = 32;
			ecc_mode = 0; /* Cannot have ECC with 32-bit bus */
			break;
		case 0x04:
			bus_width = 64;
			break;
		}
		/* Chip type (density) */
		switch (eeprom_data[1]) {
		case 0x04:
			ddr_size = 16;
			break;
		default:
			ddr_size = 8;
			break;
		}
	}

	psu_ddr_init_data_custom(ddr_size, ecc_mode, bus_width);
	if (phy_bringup)
		psu_ddr_phybringup_data_custom(ecc_mode, bus_width);
}
