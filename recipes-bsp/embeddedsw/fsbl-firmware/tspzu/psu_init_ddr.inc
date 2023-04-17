// ddr_size   : ddr device capacity 8 or 16 in Gbits. at 64b 4 and 8 Gbytes total RAM
// ecc_enable : 0=disable, 1=enable
// bus_width  : 32 or 64

// FSBL coding style...
#define psu_mask_write PSU_Mask_Write

static void psu_ddr_init_data_custom(u8 ddr_size, u8 ecc_enable, u8 bus_width)
{
	psu_mask_write(0xFD1A0108, 0x00000008U, 0x00000008U);
	psu_mask_write(0xFD070000, 0xE30FBE3DU, bus_width == 64 ? 0x81040010U : 0x81041010U);
	psu_mask_write(0xFD070010, 0x8000F03FU, 0x00000030U);
	psu_mask_write(0xFD070020, 0x000003F3U, 0x00000300U);
	psu_mask_write(0xFD070024, 0xFFFFFFFFU, 0x00800000U);
	psu_mask_write(0xFD070030, 0x0000007FU, 0x00000000U);
	psu_mask_write(0xFD070034, 0x00FFFF1FU, 0x00409410U);
	psu_mask_write(0xFD070050, 0x00F1F1F4U, 0x00210000U);
	psu_mask_write(0xFD070054, 0x0FFF0FFFU, 0x00000000U);
	psu_mask_write(0xFD070060, 0x00000073U, 0x00000001U);
	psu_mask_write(0xFD070064, 0x0FFF83FFU, ddr_size == 8 ? 0x009280D2U : 0x0092814AU);
	psu_mask_write(0xFD070070, 0x00000017U, ecc_enable == 1 ? 0xAB000014U : 0xCD000010U);
	psu_mask_write(0xFD070074, 0x00000003U, 0x00000000U);
	psu_mask_write(0xFD0700C4, 0x3F000391U, 0x10000200U);
	psu_mask_write(0xFD0700C8, 0x01FF1F3FU, 0x0048051FU);
	psu_mask_write(0xFD0700D0, 0xC3FF0FFFU, 0x00020126U);
	psu_mask_write(0xFD0700D4, 0x01FF7F0FU, 0x00020000U);
	psu_mask_write(0xFD0700D8, 0x0000FF0FU, 0x00002705U);
	psu_mask_write(0xFD0700DC, 0xFFFFFFFFU, 0x09640301U);
	psu_mask_write(0xFD0700E0, 0xFFFFFFFFU, 0x00280200U);
	psu_mask_write(0xFD0700E4, 0x00FF03FFU, 0x00210004U);
	psu_mask_write(0xFD0700E8, 0xFFFFFFFFU, 0x000006C0U);
	psu_mask_write(0xFD0700EC, 0xFFFF0000U, 0x08190000U);
	psu_mask_write(0xFD0700F0, 0x0000003FU, 0x00000010U);
	psu_mask_write(0xFD0700F4, 0x00000FFFU, 0x0000066FU);
	psu_mask_write(0xFD070100, 0x7F3F7F3FU, 0x13122813U);
	psu_mask_write(0xFD070104, 0x001F1F7FU, 0x0004041CU);
	psu_mask_write(0xFD070108, 0x3F3F3F3FU, 0x0809060FU);
	psu_mask_write(0xFD07010C, 0x3FF3F3FFU, 0x0050400CU);
	psu_mask_write(0xFD070110, 0x1F0F0F1FU, 0x0903040AU);
	psu_mask_write(0xFD070114, 0x0F0F3F1FU, 0x06060403U);
	psu_mask_write(0xFD070118, 0x0F0F000FU, 0x01010004U);
	psu_mask_write(0xFD07011C, 0x00000F0FU, 0x00000606U);
	psu_mask_write(0xFD070120, 0x7F7F7F7FU, ddr_size == 8 ? 0x05050D08U : 0x05050D0CU);
	psu_mask_write(0xFD070124, 0x40070F3FU, 0x0002040CU);
	psu_mask_write(0xFD07012C, 0x7F1F031FU, ddr_size == 8 ? 0x1308010EU : 0x1708010EU);
	psu_mask_write(0xFD070130, 0x00030F1FU, 0x00020608U);
	psu_mask_write(0xFD070180, 0xF7FF03FFU, 0x81000040U);
	psu_mask_write(0xFD070184, 0x3FFFFFFFU, 0x0201C9C2U);
	psu_mask_write(0xFD070190, 0x1FBFBF3FU, 0x048D820DU);
	psu_mask_write(0xFD070194, 0xF31F0F0FU, 0x00030304U);
	psu_mask_write(0xFD070198, 0x0FF1F1F1U, 0x07000101U);
	psu_mask_write(0xFD07019C, 0x000000F1U, 0x00000021U);
	psu_mask_write(0xFD0701A0, 0xC3FF03FFU, 0x00400003U);
	psu_mask_write(0xFD0701A4, 0x00FF00FFU, 0x00C800FFU);
	psu_mask_write(0xFD0701B0, 0x00000007U, 0x00000000U);
	psu_mask_write(0xFD0701B4, 0x00003F3FU, 0x00000B0BU);
	psu_mask_write(0xFD0701C0, 0x00000007U, 0x00000001U);
	psu_mask_write(0xFD070200, 0x0000001FU, 0x0000001FU);
	psu_mask_write(0xFD070204, 0x001F1F1FU, bus_width == 64 ? 0x001F0909U : 0x001F0808U);
	psu_mask_write(0xFD070208, 0x0F0F0F0FU, bus_width == 64 ? 0x01010100U : 0x01010101U);
	psu_mask_write(0xFD07020C, 0x0F0F0F0FU, bus_width == 64 ? 0x01010101U : 0x0F010101U);
	psu_mask_write(0xFD070210, 0x00000F0FU, 0x00000F0FU);
	psu_mask_write(0xFD070214, 0x0F0F0F0FU, bus_width == 64 ? 0x070F0707U : 0x060F0606U);
	psu_mask_write(0xFD070218, 0x8F0F0F0FU, bus_width == 64 ? 0x07070707U : 0x06060606U);
	psu_mask_write(0xFD07021C, 0x00000F0FU, ddr_size == 8 ? 0x00000F0FU : 0x00000F07U);
	psu_mask_write(0xFD070220, 0x00001F1FU, bus_width == 64 ? 0x00001F01U : 0x00001F00U);
	psu_mask_write(0xFD070224, 0x0F0F0F0FU, bus_width == 64 ? 0x07070707U : 0x06060606U);
	psu_mask_write(0xFD070228, 0x0F0F0F0FU, bus_width == 64 ? 0x07070707U : 0x06060606U);
	psu_mask_write(0xFD07022C, 0x0000000FU, bus_width == 64 ? 0x00000007U : 0x00000006U);
	psu_mask_write(0xFD070240, 0x0F1F0F7CU, 0x06000600U);
	psu_mask_write(0xFD070244, 0x00003333U, 0x00000001U);
	psu_mask_write(0xFD070250, 0x7FFF3F07U, 0x01002001U);
	psu_mask_write(0xFD070264, 0xFF00FFFFU, 0x08000040U);
	psu_mask_write(0xFD07026C, 0xFF00FFFFU, 0x08000040U);
	psu_mask_write(0xFD070280, 0xFFFFFFFFU, 0x00000000U);
	psu_mask_write(0xFD070284, 0xFFFFFFFFU, 0x00000000U);
	psu_mask_write(0xFD070288, 0xFFFFFFFFU, 0x00000000U);
	psu_mask_write(0xFD07028C, 0xFFFFFFFFU, 0x00000000U);
	psu_mask_write(0xFD070290, 0x0000FFFFU, 0x00000000U);
	psu_mask_write(0xFD070294, 0x00000001U, 0x00000001U);
	psu_mask_write(0xFD070300, 0x00000011U, bus_width == 64 ? 0x00000000U : 0x00000001U);
	psu_mask_write(0xFD07030C, 0x80000033U, 0x00000000U);
	psu_mask_write(0xFD070320, 0x00000001U, 0x00000000U);
	psu_mask_write(0xFD070400, 0x00000111U, bus_width == 64 ? 0x00000001U : 0x00000101U);
	psu_mask_write(0xFD070404, 0x000073FFU, 0x0000200FU);
	psu_mask_write(0xFD070408, 0x000073FFU, 0x0000200FU);
	psu_mask_write(0xFD070490, 0x00000001U, 0x00000001U);
	psu_mask_write(0xFD070494, 0x0033000FU, 0x0020000BU);
	psu_mask_write(0xFD070498, 0x07FF07FFU, 0x00000000U);
	psu_mask_write(0xFD0704B4, 0x000073FFU, 0x0000200FU);
	psu_mask_write(0xFD0704B8, 0x000073FFU, 0x0000200FU);
	psu_mask_write(0xFD070540, 0x00000001U, 0x00000001U);
	psu_mask_write(0xFD070544, 0x03330F0FU, 0x02000B03U);
	psu_mask_write(0xFD070548, 0x07FF07FFU, 0x00000000U);
	psu_mask_write(0xFD070564, 0x000073FFU, 0x0000200FU);
	psu_mask_write(0xFD070568, 0x000073FFU, 0x0000200FU);
	psu_mask_write(0xFD0705F0, 0x00000001U, 0x00000001U);
	psu_mask_write(0xFD0705F4, 0x03330F0FU, 0x02000B03U);
	psu_mask_write(0xFD0705F8, 0x07FF07FFU, 0x00000000U);
	psu_mask_write(0xFD070614, 0x000073FFU, 0x0000200FU);
	psu_mask_write(0xFD070618, 0x000073FFU, 0x0000200FU);
	psu_mask_write(0xFD0706A0, 0x00000001U, 0x00000001U);
	psu_mask_write(0xFD0706A4, 0x0033000FU, 0x00100003U);
	psu_mask_write(0xFD0706A8, 0x07FF07FFU, 0x0000004FU);
	psu_mask_write(0xFD0706AC, 0x0033000FU, 0x00100003U);
	psu_mask_write(0xFD0706B0, 0x000007FFU, 0x0000004FU);
	psu_mask_write(0xFD0706C4, 0x000073FFU, 0x0000200FU);
	psu_mask_write(0xFD0706C8, 0x000073FFU, 0x0000200FU);
	psu_mask_write(0xFD070750, 0x00000001U, 0x00000001U);
	psu_mask_write(0xFD070754, 0x0033000FU, 0x00100003U);
	psu_mask_write(0xFD070758, 0x07FF07FFU, 0x0000004FU);
	psu_mask_write(0xFD07075C, 0x0033000FU, 0x00100003U);
	psu_mask_write(0xFD070760, 0x000007FFU, 0x0000004FU);
	psu_mask_write(0xFD070774, 0x000073FFU, 0x0000200FU);
	psu_mask_write(0xFD070778, 0x000073FFU, 0x0000200FU);
	psu_mask_write(0xFD070800, 0x00000001U, 0x00000001U);
	psu_mask_write(0xFD070804, 0x0033000FU, 0x00100003U);
	psu_mask_write(0xFD070808, 0x07FF07FFU, 0x0000004FU);
	psu_mask_write(0xFD07080C, 0x0033000FU, 0x00100003U);
	psu_mask_write(0xFD070810, 0x000007FFU, 0x0000004FU);
	psu_mask_write(0xFD070F04, 0x000001FFU, 0x00000000U);
	psu_mask_write(0xFD070F08, 0x000000FFU, 0x00000000U);
	psu_mask_write(0xFD070F0C, 0x000001FFU, 0x00000010U);
	psu_mask_write(0xFD070F10, 0x000000FFU, 0x0000000FU);
	psu_mask_write(0xFD072190, 0x1FBFBF3FU, 0x07828002U);
	psu_mask_write(0xFD1A0108, 0x0000000CU, 0x00000000U);
	psu_mask_write(0xFD080010, 0xFFFFFFFFU, 0x07001E00U);
	psu_mask_write(0xFD080018, 0xFFFFFFFFU, 0x00F12090U);
	psu_mask_write(0xFD08001C, 0xFFFFFFFFU, 0x55AA5480U);
	psu_mask_write(0xFD080024, 0xFFFFFFFFU, 0x010100F4U);
	psu_mask_write(0xFD080040, 0xFFFFFFFFU, 0x4B025810U);
	psu_mask_write(0xFD080044, 0xFFFFFFFFU, 0xEA601518U);
	psu_mask_write(0xFD080068, 0xFFFFFFFFU, 0x000E0000U);
	psu_mask_write(0xFD080090, 0xFFFFFFFFU, 0x02A04161U);
	psu_mask_write(0xFD0800C0, 0xFFFFFFFFU, 0x00000000U);
	psu_mask_write(0xFD0800C4, 0xFFFFFFFFU, 0x000000E0U);//0x000000E5U
	psu_mask_write(0xFD080100, 0xFFFFFFFFU, 0x0800040CU);
	psu_mask_write(0xFD080110, 0xFFFFFFFFU, 0x08261109U);
	psu_mask_write(0xFD080114, 0xFFFFFFFFU, 0x28240008U);
	psu_mask_write(0xFD080118, 0xFFFFFFFFU, 0x000F0300U);
	psu_mask_write(0xFD08011C, 0xFFFFFFFFU, 0x83000800U);
	psu_mask_write(0xFD080120, 0xFFFFFFFFU, ddr_size == 8 ? 0x01A42B08U : 0x02942B08U);
	psu_mask_write(0xFD080124, 0xFFFFFFFFU, 0x00381109U);
	psu_mask_write(0xFD080128, 0xFFFFFFFFU, 0x00001011U);
	psu_mask_write(0xFD080140, 0xFFFFFFFFU, 0x08400020U);
	psu_mask_write(0xFD080144, 0xFFFFFFFFU, 0x00000C80U);
	psu_mask_write(0xFD080150, 0xFFFFFFFFU, 0x00000000U);
	psu_mask_write(0xFD080154, 0xFFFFFFFFU, 0x00000300U);
	psu_mask_write(0xFD080180, 0xFFFFFFFFU, 0x00000864U);
	psu_mask_write(0xFD080184, 0xFFFFFFFFU, 0x00000301U);
	psu_mask_write(0xFD080188, 0xFFFFFFFFU, 0x00000028U);
	psu_mask_write(0xFD08018C, 0xFFFFFFFFU, 0x00000200U);
	psu_mask_write(0xFD080190, 0xFFFFFFFFU, 0x00000000U);
	psu_mask_write(0xFD080194, 0xFFFFFFFFU, 0x000006C0U);
	psu_mask_write(0xFD080198, 0xFFFFFFFFU, 0x00000819U);
	psu_mask_write(0xFD0801AC, 0xFFFFFFFFU, 0x00000000U);
	psu_mask_write(0xFD0801B0, 0xFFFFFFFFU, 0x0000004DU);
	psu_mask_write(0xFD0801B4, 0xFFFFFFFFU, 0x00000008U);
	psu_mask_write(0xFD0801B8, 0xFFFFFFFFU, 0x0000004DU);
	psu_mask_write(0xFD0801D8, 0xFFFFFFFFU, 0x00000000U);
	psu_mask_write(0xFD080200, 0xFFFFFFFFU, 0x800091C7U);
	psu_mask_write(0xFD080204, 0xFFFFFFFFU, 0x00010236U);
	psu_mask_write(0xFD080240, 0xFFFFFFFFU, 0x00141054U);
	psu_mask_write(0xFD080250, 0xFFFFFFFFU, 0x00088000U);
	psu_mask_write(0xFD080414, 0xFFFFFFFFU, ddr_size == 8 ? (bus_width == 64 ? 0x12341000U : 0x12340800U) : (bus_width == 64 ? 0x12342000U : 0x12341000U));
	psu_mask_write(0xFD0804F4, 0xFFFFFFFFU, 0x00000005U);
	psu_mask_write(0xFD080500, 0xFFFFFFFFU, 0x30000028U);
	psu_mask_write(0xFD080508, 0xFFFFFFFFU, 0x0A000000U);
	psu_mask_write(0xFD08050C, 0xFFFFFFFFU, 0x00000009U);
	psu_mask_write(0xFD080510, 0xFFFFFFFFU, 0x0A000000U);
	psu_mask_write(0xFD080520, 0xFFFFFFFFU, 0x0300B0CEU);
	psu_mask_write(0xFD080528, 0xFFFFFFFFU, 0xF9032019U);
	psu_mask_write(0xFD08052C, 0xFFFFFFFFU, 0x07F001E3U);
	psu_mask_write(0xFD080544, 0xFFFFFFFFU, 0x00000000U);
	psu_mask_write(0xFD080548, 0xFFFFFFFFU, 0x00000000U);
	psu_mask_write(0xFD080558, 0xFFFFFFFFU, 0x00000000U);
	psu_mask_write(0xFD08055C, 0xFFFFFFFFU, 0x00000000U);
	psu_mask_write(0xFD080560, 0xFFFFFFFFU, 0x00000000U);
	psu_mask_write(0xFD080564, 0xFFFFFFFFU, 0x00000000U);
	psu_mask_write(0xFD080680, 0xFFFFFFFFU, 0x008AEA58U);
	psu_mask_write(0xFD080684, 0xFFFFFFFFU, 0x000079DDU);
	psu_mask_write(0xFD080694, 0xFFFFFFFFU, 0x01E10210U);
	psu_mask_write(0xFD080698, 0xFFFFFFFFU, 0x01E10000U);
	psu_mask_write(0xFD0806A4, 0xFFFFFFFFU, 0x00087BDBU);
	psu_mask_write(0xFD080700, 0xFFFFFFFFU, 0x40800604U);
	psu_mask_write(0xFD080704, 0xFFFFFFFFU, 0x00007FFFU);
	psu_mask_write(0xFD08070C, 0xFFFFFFFFU, 0x3F000008U);
	psu_mask_write(0xFD080710, 0xFFFFFFFFU, 0x0E00B03CU);
	psu_mask_write(0xFD080714, 0xFFFFFFFFU, 0x09094F4FU);
	psu_mask_write(0xFD080718, 0xFFFFFFFFU, 0x09092B2BU);
	psu_mask_write(0xFD080800, 0xFFFFFFFFU, 0x40800604U);
	psu_mask_write(0xFD080804, 0xFFFFFFFFU, 0x00007FFFU);
	psu_mask_write(0xFD08080C, 0xFFFFFFFFU, 0x3F000008U);
	psu_mask_write(0xFD080810, 0xFFFFFFFFU, 0x0E00B03CU);
	psu_mask_write(0xFD080814, 0xFFFFFFFFU, 0x09094F4FU);
	psu_mask_write(0xFD080818, 0xFFFFFFFFU, 0x09092B2BU);
	psu_mask_write(0xFD080900, 0xFFFFFFFFU, 0x40800604U);
	psu_mask_write(0xFD080904, 0xFFFFFFFFU, 0x00007FFFU);
	psu_mask_write(0xFD08090C, 0xFFFFFFFFU, 0x3F000008U);
	psu_mask_write(0xFD080910, 0xFFFFFFFFU, 0x0E00B004U);
	psu_mask_write(0xFD080914, 0xFFFFFFFFU, 0x09094F4FU);
	psu_mask_write(0xFD080918, 0xFFFFFFFFU, 0x09092B2BU);
	psu_mask_write(0xFD080A00, 0xFFFFFFFFU, 0x40800604U);
	psu_mask_write(0xFD080A04, 0xFFFFFFFFU, 0x00007FFFU);
	psu_mask_write(0xFD080A0C, 0xFFFFFFFFU, 0x3F000008U);
	psu_mask_write(0xFD080A10, 0xFFFFFFFFU, 0x0E00B004U);
	psu_mask_write(0xFD080A14, 0xFFFFFFFFU, 0x09094F4FU);
	psu_mask_write(0xFD080A18, 0xFFFFFFFFU, 0x09092B2BU);
	psu_mask_write(0xFD080B00, 0xFFFFFFFFU, bus_width == 64 ? 0x40800604U : 0x80803660U);
	psu_mask_write(0xFD080B04, 0xFFFFFFFFU, bus_width == 64 ? 0x00007FFFU : 0x55556000U);
	psu_mask_write(0xFD080B08, 0xFFFFFFFFU, bus_width == 64 ? 0x00000000U : 0xAAAAAAAAU);
	psu_mask_write(0xFD080B0C, 0xFFFFFFFFU, bus_width == 64 ? 0x3F000008U : 0x0029A4A4U);
	psu_mask_write(0xFD080B10, 0xFFFFFFFFU, bus_width == 64 ? 0x0E00B004U : 0x0C00B000U);
	psu_mask_write(0xFD080B14, 0xFFFFFFFFU, 0x09094F4FU);
	psu_mask_write(0xFD080B18, 0xFFFFFFFFU, 0x09092B2BU);
	psu_mask_write(0xFD080C00, 0xFFFFFFFFU, bus_width == 64 ? 0x40800604U : 0x80803660U);
	psu_mask_write(0xFD080C04, 0xFFFFFFFFU, bus_width == 64 ? 0x00007FFFU : 0x55556000U);
	psu_mask_write(0xFD080C08, 0xFFFFFFFFU, bus_width == 64 ? 0x00000000U : 0xAAAAAAAAU);
	psu_mask_write(0xFD080C0C, 0xFFFFFFFFU, bus_width == 64 ? 0x3F000008U : 0x0029A4A4U);
	psu_mask_write(0xFD080C10, 0xFFFFFFFFU, bus_width == 64 ? 0x0E00B03CU : 0x0C00B000U);
	psu_mask_write(0xFD080C14, 0xFFFFFFFFU, 0x09094F4FU);
	psu_mask_write(0xFD080C18, 0xFFFFFFFFU, 0x09092B2BU);
	psu_mask_write(0xFD080D00, 0xFFFFFFFFU, bus_width == 64 ? 0x40800604U : 0x80803660U);
	psu_mask_write(0xFD080D04, 0xFFFFFFFFU, bus_width == 64 ? 0x00007FFFU : 0x55556000U);
	psu_mask_write(0xFD080D08, 0xFFFFFFFFU, bus_width == 64 ? 0x00000000U : 0xAAAAAAAAU);
	psu_mask_write(0xFD080D0C, 0xFFFFFFFFU, bus_width == 64 ? 0x3F000008U : 0x0029A4A4U);
	psu_mask_write(0xFD080D10, 0xFFFFFFFFU, bus_width == 64 ? 0x0E00B004U : 0x0C00B000U);
	psu_mask_write(0xFD080D14, 0xFFFFFFFFU, 0x09094F4FU);
	psu_mask_write(0xFD080D18, 0xFFFFFFFFU, 0x09092B2BU);
	psu_mask_write(0xFD080E00, 0xFFFFFFFFU, bus_width == 64 ? 0x40800604U : 0x80803660U);
	psu_mask_write(0xFD080E04, 0xFFFFFFFFU, bus_width == 64 ? 0x00007FFFU : 0x55556000U);
	psu_mask_write(0xFD080E08, 0xFFFFFFFFU, bus_width == 64 ? 0x00000000U : 0xAAAAAAAAU);
	psu_mask_write(0xFD080E0C, 0xFFFFFFFFU, bus_width == 64 ? 0x3F000008U : 0x0029A4A4U);
	psu_mask_write(0xFD080E10, 0xFFFFFFFFU, bus_width == 64 ? 0x0E00B03CU : 0x0C00B000U);
	psu_mask_write(0xFD080E14, 0xFFFFFFFFU, 0x09094F4FU);
	psu_mask_write(0xFD080E18, 0xFFFFFFFFU, 0x09092B2BU);
	psu_mask_write(0xFD080F00, 0xFFFFFFFFU, ecc_enable == 1 ? 0x40800604U : 0x80803660U);
	psu_mask_write(0xFD080F04, 0xFFFFFFFFU, ecc_enable == 1 ? 0x00007FFFU : 0x55556000U);
	psu_mask_write(0xFD080F08, 0xFFFFFFFFU, ecc_enable == 1 ? 0x00000000U : 0xAAAAAAAAU);
	psu_mask_write(0xFD080F0C, 0xFFFFFFFFU, ecc_enable == 1 ? 0x3F000008U : 0x0029A4A4U);
	psu_mask_write(0xFD080F10, 0xFFFFFFFFU, ecc_enable == 1 ? 0x0E00B03CU : 0x0C00B000U);
	psu_mask_write(0xFD080F14, 0xFFFFFFFFU, 0x09094F4FU);
	psu_mask_write(0xFD080F18, 0xFFFFFFFFU, 0x09092B2BU);
	psu_mask_write(0xFD081400, 0xFFFFFFFFU, 0x2A019FFEU);
	psu_mask_write(0xFD081404, 0xFFFFFFFFU, 0x000E0000U);
	psu_mask_write(0xFD08141C, 0xFFFFFFFFU, 0x01264300U);
	psu_mask_write(0xFD08142C, 0xFFFFFFFFU, 0x00041800U);
	psu_mask_write(0xFD081430, 0xFFFFFFFFU, 0x70800000U);
	psu_mask_write(0xFD081440, 0xFFFFFFFFU, 0x2A019FFEU);
	psu_mask_write(0xFD081444, 0xFFFFFFFFU, 0x000E0000U);
	psu_mask_write(0xFD08145C, 0xFFFFFFFFU, 0x01264300U);
	psu_mask_write(0xFD08146C, 0xFFFFFFFFU, 0x00041800U);
	psu_mask_write(0xFD081470, 0xFFFFFFFFU, 0x70800000U);
	psu_mask_write(0xFD081480, 0xFFFFFFFFU, bus_width == 64 ? 0x2A019FFEU : 0x15019FFEU);
	psu_mask_write(0xFD081484, 0xFFFFFFFFU, bus_width == 64 ? 0x000E0000U : 0x200E0000U);
	psu_mask_write(0xFD08149C, 0xFFFFFFFFU, bus_width == 64 ? 0x01264300U : 0x01266300U);
	psu_mask_write(0xFD0814AC, 0xFFFFFFFFU, 0x00041800U);
	psu_mask_write(0xFD0814B0, 0xFFFFFFFFU, bus_width == 64 ? 0x70800000U : 0x70400000U);
	psu_mask_write(0xFD0814C0, 0xFFFFFFFFU, bus_width == 64 ? 0x2A019FFEU : 0x15019FFEU);
	psu_mask_write(0xFD0814C4, 0xFFFFFFFFU, bus_width == 64 ? 0x000E0000U : 0x200E0000U);
	psu_mask_write(0xFD0814DC, 0xFFFFFFFFU, bus_width == 64 ? 0x01264300U : 0x01266300U);
	psu_mask_write(0xFD0814EC, 0xFFFFFFFFU, 0x00041800U);
	psu_mask_write(0xFD0814F0, 0xFFFFFFFFU, bus_width == 64 ? 0x70800000U : 0x70400000U);
	psu_mask_write(0xFD081500, 0xFFFFFFFFU, ecc_enable == 1 ? 0x2A019FFEU : 0x15019FFEU);
	psu_mask_write(0xFD081504, 0xFFFFFFFFU, ecc_enable == 1 ? 0x000E0000U : 0x200E0000U);
	psu_mask_write(0xFD08151C, 0xFFFFFFFFU, ecc_enable == 1 ? 0x01264300U : 0x01266300U);
	psu_mask_write(0xFD08152C, 0xFFFFFFFFU, 0x00041800U);
	psu_mask_write(0xFD081530, 0xFFFFFFFFU, ecc_enable == 1 ? 0x70800000U : 0x70400000U);
	psu_mask_write(0xFD0817DC, 0xFFFFFFFFU, 0x012643C4U);
}

static int psu_ddr_phybringup_data_custom(u8 ecc_enabled, u8 bus_width)
{
	unsigned int regval = 0;
	unsigned int pll_retry = 10;
	unsigned int pll_locked = 0;
	unsigned int timeout = 10000000;

	while ((pll_retry > 0) && (!pll_locked)) {
		Xil_Out32(0xFD080004, 0x00040010);
		Xil_Out32(0xFD080004, 0x00040011);
		timeout = 10000000;
		while (((Xil_In32(0xFD080030) & 0x1) != 1) && timeout > 0){
			timeout--;
		}
		if(timeout > 0){
			
			pll_locked = (Xil_In32(0xFD080030) & 0x80000000) >> 31;
			pll_locked &= (Xil_In32(0xFD0807E0) & 0x10000) >> 16; // DX0GSR0
			pll_locked &= (Xil_In32(0xFD0809E0) & 0x10000) >> 16; // DX2GSR0
			if(bus_width > 32){
				pll_locked &= (Xil_In32(0xFD080BE0) & 0x10000) >> 16; // DX4GSR0
				pll_locked &= (Xil_In32(0xFD080DE0) & 0x10000) >> 16; // DX6GSR0
				if(ecc_enabled == 1)
					pll_locked &= (Xil_In32(0xFD080FE0) & 0x10000) 	>> 16; // DX8GSR0
			}

		}else{
			pll_locked = 0;
		}
		pll_retry--;
	}
	// Write pll_retry into upper GPR1
	Xil_Out32(0xFD0800C4, Xil_In32(0xFD0800C4) | (pll_retry << 16));
	if (!pll_locked)
		return 0;


	// Start init phy
	Xil_Out32(0xFD080004U, 0x00040063U);
	timeout = 10000000;
	while (((Xil_In32(0xFD080030U) & 0x0000000FU) != 0x0000000FU) && timeout > 0){
		timeout--;
	}
	if(timeout == 0)
		return 0;
	
	prog_reg(0xFD080004U, 0x00000001U, 0x00000000U, 0x00000001U);
	timeout = 10000000;
	while (((Xil_In32(0xFD080030U) & 0x000000FFU) != 0x0000001FU) && timeout > 0){
		timeout--;
	}
	if(timeout == 0)
		return 0;

	Xil_Out32(0xFD0701B0U, 0x00000001U);
	Xil_Out32(0xFD070320U, 0x00000001U);
	timeout = 10000000;
	while (((Xil_In32(0xFD070004U) & 0x0000000FU) != 0x00000001U) && timeout > 0){
		timeout--;
	}
	if(timeout == 0)
		return 0;

	prog_reg(0xFD080014U, 0x00000040U, 0x00000006U, 0x00000001U);
	Xil_Out32(0xFD080004, 0x0004FE01);
	regval = Xil_In32(0xFD080030);
	timeout = 10000000;
	while (regval != 0x80000FFF && timeout > 0){
		regval = Xil_In32(0xFD080030);
		timeout--;
	}
	if(timeout == 0)
		return 0;
		
	regval = ((Xil_In32(0xFD080030) & 0x1FFF0000) >> 18);
	if (regval != 0)
		return 0;

	Xil_Out32(0xFD080200U, 0x100091C7U);
	int cur_R006_tREFPRD;

	cur_R006_tREFPRD = (Xil_In32(0xFD080018U) & 0x0003FFFFU) >> 0x00000000U;
	prog_reg(0xFD080018, 0x3FFFF, 0x0, cur_R006_tREFPRD);

	prog_reg(0xFD08001CU, 0x00000018U, 0x00000003U, 0x00000003U);
	prog_reg(0xFD08142CU, 0x00000030U, 0x00000004U, 0x00000003U); 
	prog_reg(0xFD08146CU, 0x00000030U, 0x00000004U, 0x00000003U);
	prog_reg(0xFD0814ACU, 0x00000030U, 0x00000004U, 0x00000003U);
	prog_reg(0xFD0814ECU, 0x00000030U, 0x00000004U, 0x00000003U);
	prog_reg(0xFD08152CU, 0x00000030U, 0x00000004U, 0x00000003U);

	Xil_Out32(0xFD080004, 0x00060001);
	regval = Xil_In32(0xFD080030);
	timeout = 10000000;
	// Check if training/initialization is done
	while (((regval & 0x80004001) != 0x80004001) && timeout > 0){
		regval = Xil_In32(0xFD080030);
		timeout--;
	}
	if(timeout == 0)
		return 0;
	// Check for any errors
	regval = ((Xil_In32(0xFD080030) & 0x1FFF0000) >> 18);
	if (regval != 0)
		return 0;

	prog_reg(0xFD08001CU, 0x00000018U, 0x00000003U, 0x00000000U);
	prog_reg(0xFD08142CU, 0x00000030U, 0x00000004U, 0x00000000U);
	prog_reg(0xFD08146CU, 0x00000030U, 0x00000004U, 0x00000000U);
	prog_reg(0xFD0814ACU, 0x00000030U, 0x00000004U, 0x00000000U);
	prog_reg(0xFD0814ECU, 0x00000030U, 0x00000004U, 0x00000000U);
	prog_reg(0xFD08152CU, 0x00000030U, 0x00000004U, 0x00000000U);

	Xil_Out32(0xFD080200U, 0x800091C7U);
	prog_reg(0xFD080018, 0x3FFFF, 0x0, cur_R006_tREFPRD);

	Xil_Out32(0xFD080004, 0x0000C001);
	regval = Xil_In32(0xFD080030);
	timeout = 10000000;
	while (((regval & 0x80000C01) != 0x80000C01) && timeout > 0){
		regval = Xil_In32(0xFD080030);
		timeout--;
	}
	if(timeout == 0)
		return 0;
		

	Xil_Out32(0xFD070180U, 0x01000040U);
	Xil_Out32(0xFD070060U, 0x00000000U);
	prog_reg(0xFD080014U, 0x00000040U, 0x00000006U, 0x00000000U);

	return 1;
}
