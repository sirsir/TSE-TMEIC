package jp.co.tmeic.mespd.constant;

public final class MesErrorMessageKey {

	/** コンストラクタ */
	private MesErrorMessageKey() {
	}

	/** 製造計画 未開始 */
	public static final String MANUFACTURING_PLAN_NOT_STARTED = "errors.manufacturing.plan.not.started";

	/** 製造計画 停止中 */
	public static final String MANUFACTURING_PLAN_STOP = "errors.manufacturing.plan.stop";

	/** 工程計画 未開始 */
	public static final String PROCESS_PLAN_NOT_STARTED = "errors.process.plan.not.started";

	/** 工程計画 完了済 */
	public static final String PROCESS_PLAN_FINISHED = "errors.process.plan.finished";
}
