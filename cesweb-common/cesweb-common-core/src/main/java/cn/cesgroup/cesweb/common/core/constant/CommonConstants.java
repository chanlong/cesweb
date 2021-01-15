package cn.cesgroup.cesweb.common.core.constant;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 2:03:05 PM
 * @version 1.0.2020
 */
public interface CommonConstants {

	/**
	 * header 中租户ID
	 */
	String TENANT_ID = "TENANT-ID";

	/**
	 * header 中版本信息
	 */
	String VERSION = "VERSION";

	/**
	 * 租户ID
	 */
	String TENANT_ID_1 = "1";

	/**
	 * 删除
	 */
	String STATUS_DEL = "1";

	/**
	 * 正常
	 */
	String STATUS_NORMAL = "0";

	/**
	 * 锁定
	 */
	String STATUS_LOCK = "9";

	/**
	 * 菜单树根节点
	 */
	String MENU_TREE_ROOT_ID = "-1";

	/**
	 * 编码
	 */
	String UTF8 = "UTF-8";

	/**
	 * 前端工程名
	 */
	String FRONT_END_PROJECT = "cesweb-ui";

	/**
	 * 后端工程名
	 */
	String BACK_END_PROJECT = "cesweb";

	/**
	 * 公共参数
	 */
	String PUBLIC_PARAM_KEY = "PUBLIC_PARAM_KEY";

	/**
	 * 成功标记
	 */
	Integer SUCCESS = 0;

	/**
	 * 失败标记
	 */
	Integer FAIL = 1;

	/**
	 * 默认存储bucket
	 */
	String BUCKET_NAME = "chanlong";

	/**
	 * 滑块验证码
	 */
	String IMAGE_CODE_TYPE = "blockPuzzle";

}
