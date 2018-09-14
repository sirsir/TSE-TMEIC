package org.seasar.struts.unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.PropertyMessageResourcesFactory;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.convention.NamingConvention;
import org.seasar.framework.mock.servlet.MockServletContext;
import org.seasar.struts.action.S2RequestProcessor;
import org.seasar.struts.config.S2ActionMapping;
import org.seasar.struts.config.S2ModuleConfig;
import org.seasar.struts.util.ActionUtil;
import org.seasar.struts.util.S2ExecuteConfigUtil;
import org.seasar.struts.validator.S2ValidatorPlugIn;

public class S2ActionTestCase extends S2TestCase {

	protected S2ModuleConfig moduleConfig;
	protected S2ActionMapping amapp;
	protected Class<?> clazz;
	// private Object action;
	// private ActionForm form;

	{
		super.setRegisterNamingConvention(false);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		// コンテナの初期化が実行される前に必要な設定をほげほげします。
		moduleConfig = new S2ModuleConfig("");
		getServletContext().setAttribute(Globals.MODULE_KEY,
				moduleConfig);

		S2ValidatorPlugIn vp = new S2ValidatorPlugIn();
		// クラスパスに追加しないとだめだresourcesフォルダにこぴるのが早い。どうせいじらないし。
		vp.setPathnames(getValidationRuleXmlPath());
		final MockServletContext servletContext = getServletContext();
		vp.init(new ActionServlet() {
			private static final long serialVersionUID = 1L;

			@Override
			public ServletContext getServletContext() {
				return servletContext;
			}
		}, moduleConfig);
//		getServletContext().setAttribute(ValidatorPlugIn.VALIDATOR_KEY,
//				new S2ValidatorResources());

		getServletContext().setAttribute(Globals.MESSAGES_KEY,
				PropertyMessageResourcesFactory.createFactory().createResources("application.properties")
				);
	}

	/**
	 * バリデーションルールのxmlのパスを記述します。
	 * defaultは"validator-rules.xml"になっています。
	 * xmlはクラスパスに追加してある必要があります。
	 * WEB-INF配下をクラスパスに追加するか、
	 * test/resourcesにファイルをコピーすると良いです。
	 * @return 読み込むバリデーションルールのxmlのパス
	 */
	protected String getValidationRuleXmlPath() {
		return "validator-rules.xml";
	}

	/**
	 * アクションのコンポーネントをコンテナから取得します。
	 *
	 * @param <T>
	 * @param clazz
	 * @return　Actionオブジェクト
	 */
	@SuppressWarnings("unchecked")
	protected <T> T initAction(Class<T> clazz) {

		// Actionコンポーネントを呼び出し、コンテナにクラスを登録させる（HotDeploy、WarmDeploy時に必要）
		SingletonS2Container.getComponent(clazz);

		// 指定されたActionクラスを手動でいろいろ設定とか探して、Attributeに設定します。
		NamingConvention nc = (NamingConvention) getContainer().getComponent(NamingConvention.class);

		// Actionに関する名前作成
		String className = clazz.getName();
		String actionComponentName = nc.fromClassNameToComponentName(className);
		String actionPath = ActionUtil.fromActionNameToPath(actionComponentName);

		amapp = (S2ActionMapping) moduleConfig.findActionConfig(actionPath);

		// 通常は"/dept.do?SAStruts.method=sampleMethod"や"/dept/hoge"といった風に遷移先を計算して返すが
		// テストケースが書きにくいため計算させないでActionのreturnの文字列をそのまま返すようにする。
		// 遷移先を厳密にチェックしたいもしくはAStrutsのロジックを出来るだけ通るようにしたいならば、
		// 逆にこのクラスを利用しないようにこの行をコメントにする。
		amapp = new DummyS2ActionMapping(amapp);

		return (T) amapp.getAction();
	}

	protected String executeAction(String methodName) {

		// リクエストにExecuteConfigをセットする
		getRequest().setAttribute(S2ExecuteConfigUtil.class.getName(),
				amapp.getExecuteConfig(methodName));

		MockS2RequestProcessor r = new MockS2RequestProcessor(moduleConfig);
		ActionForm f = r.processActionForm(getRequest(), getResponse(), amapp);

		ActionForward af = null;
		try {
			//Actionクラス生成
	        Action aw = r.processActionCreate(getRequest(), getResponse(), amapp);
	        //実行
	        af = r.processActionPerform(getRequest(), getResponse(), aw, f, amapp);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		if (af == null) {
			return null;
		}

		return af.getPath();
	}

	protected ActionMessages getErrors() {
		return (ActionMessages) getRequest().getAttribute(Globals.ERROR_KEY);
	}

	@SuppressWarnings("unchecked")
	protected List<ActionMessage> getErrorsMessage() {

		if (getErrors() == null || getErrors().isEmpty()) {
			return Collections.emptyList();
		}

		List<ActionMessage> list = new ArrayList<ActionMessage>();
		Iterator<ActionMessage> ite = (Iterator<ActionMessage>) getErrors().get();
		while (ite.hasNext()) {
			list.add(ite.next());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	protected List<ActionMessage> getErrorsMessage(String propertyName) {

		if (getErrors() == null || getErrors().isEmpty()) {
			return Collections.emptyList();
		}

		List<ActionMessage> list = new ArrayList<ActionMessage>();
		Iterator<ActionMessage> ite = (Iterator<ActionMessage>) getErrors().get(propertyName);
		while (ite.hasNext()) {
			list.add(ite.next());
		}
		return list;
	}

	/**
	 * 通常は"/dept.do?SAStruts.method=sampleMethod"や"/dept/hoge"といった風に
	 * 遷移先を計算して返すが、テストケースが書きにくいため計算させないようにする。
	 * 遷移先を厳密にチェックしたいもしくは
	 * SAStrutsのロジックを出来るだけ通るようにしたいならば、
	 * 逆にこのクラスを利用しないようにする。
	 */
	private static class DummyS2ActionMapping extends S2ActionMapping {
		private static final long serialVersionUID = 1L;

		// private final S2ActionMapping org;

		public DummyS2ActionMapping(S2ActionMapping org) {
			// //オリジナルを一応取っておく？
			// this.org = org;

			// 全ての値をコピーする
			this.actionBeanDesc = org.getActionBeanDesc();
			this.actionFormBeanDesc = org.getActionFormBeanDesc();
			this.actionFormComponentDef = org.getActionFormComponentDef();
			this.actionFormField = org.getActionFormField();
			this.attribute = org.getAttribute();
			this.cancellable = org.getCancellable();
			this.componentDef = org.getComponentDef();

			// this.exceptions =
			ExceptionConfig[] exceptionConfigs = org.findExceptionConfigs();
			for (ExceptionConfig config : exceptionConfigs) {
				addExceptionConfig(config);
			}
			// this.executeConfigs =
			String[] executeMethodNames = org.getExecuteMethodNames();
			for (String key : executeMethodNames) {
				addExecuteConfig(org.getExecuteConfig(key));
			}
			this.forward = org.getForward();
			// this.forwards =
			ForwardConfig[] findForwardConfigs = findForwardConfigs();
			for (ForwardConfig forwardConfig : findForwardConfigs) {
				addForwardConfig(forwardConfig);
			}
			this.configured = true;
			this.include = org.getInclude();
			this.input = org.getInput();
			this.moduleConfig = org.getModuleConfig();
			this.name = org.getName();
			this.parameter = org.getParameter();
			this.path = org.getPath();
			this.prefix = org.getPrefix();
			this.roleNames = org.getRoleNames();
			this.roles = org.getRoles();
			this.scope = org.getScope();
			this.suffix = org.getSuffix();
			this.type = org.getType();
			this.unknown = org.getUnknown();
			this.validate = org.getValidate();
		}

		@Override
		public ActionForward createForward(String path, boolean redirect) {
			// コンテキストパスなどの再計算を行わないようにする。
			return new ActionForward(path, redirect);
		}
	}

	/**
	 * おれおれパッケージでprotectedメソッドにアクセス。
	 *
	 */
	private static class MockS2RequestProcessor extends S2RequestProcessor {

		public MockS2RequestProcessor(ModuleConfig moduleConfig) {
			this.moduleConfig = moduleConfig;
		}

		@Override
		protected ActionForward processActionPerform(
				HttpServletRequest request, HttpServletResponse response,
				Action action, ActionForm form, ActionMapping mapping)
				throws IOException, ServletException {
			return super.processActionPerform(request, response, action, form, mapping);
		}

		@Override
		protected Action processActionCreate(HttpServletRequest request,
				HttpServletResponse response, ActionMapping mapping)
				throws IOException {
			return super.processActionCreate(request, response, mapping);
		}

		// Formの作成、Request or Sessionに登録をする
		@Override
		protected ActionForm processActionForm(HttpServletRequest request,
				HttpServletResponse response, ActionMapping mapping) {
			return super.processActionForm(request, response, mapping);
		}
	}
}
