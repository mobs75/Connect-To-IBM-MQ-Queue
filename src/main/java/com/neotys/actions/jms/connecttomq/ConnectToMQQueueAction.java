package com.neotys.actions.jms.connecttomq;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.google.common.base.Optional;
import com.neotys.actions.jms.connecttomq.ConnectToMQQueueArguments.AppearsByDefault;
import com.neotys.actions.jms.connecttomq.ConnectToMQQueueArguments.ConnectToQueueOption;
import com.neotys.extensions.action.Action;
import com.neotys.extensions.action.ActionParameter;
import com.neotys.extensions.action.engine.ActionEngine;

/**
 * @author srichert
 */
public final class ConnectToMQQueueAction implements Action {

	private static final String BUNDLE_NAME = "com.neotys.actions.jms.connecttomq.bundle";
	private static final String DISPLAY_NAME = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault()).getString("displayName");
	private static final String DISPLAY_PATH = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault()).getString("displayPath");

	/**
	 * logo
	 */
	private static final ImageIcon LOGO_ICON;

	static {
		final URL iconURL = ConnectToMQQueueAction.class.getResource("message.png");
		if (iconURL != null) {
			LOGO_ICON = new ImageIcon(iconURL);
		} else {
			LOGO_ICON = null;
		}
	}

	@Override
	public String getType() {
		return "Connect To MQ Queue";
	}

	@Override
	public List<ActionParameter> getDefaultActionParameters() {
		final ArrayList<ActionParameter> parameters = new ArrayList<>();

		for (final ConnectToQueueOption option : ConnectToQueueOption.values()) {
			if (AppearsByDefault.True.equals(option.getAppearsByDefault())) {
				parameters.add(new ActionParameter(option.getName(), option.getDefaultValue(),
						option.getType()));
			}
		}

		return parameters;
	}

	@Override
	public boolean getDefaultIsHit(){
		return true;
	}

	@Override
	public Class<? extends ActionEngine> getEngineClass() {
		return ConnectToMQQueueActionEngine.class;
	}

	@Override
	public Icon getIcon() {
		return LOGO_ICON;
	}

	@Override
	public String getDescription() {
		return ConnectToMQQueueArguments.getArgumentDescriptions();
	}

	@Override
	public String getDisplayName() {
		return DISPLAY_NAME;
	}

	@Override
	public String getDisplayPath() {
		return DISPLAY_PATH;
	}

	@Override
	public Optional<String> getMinimumNeoLoadVersion() {
		return Optional.of("5.1");
	}

	@Override
	public Optional<String> getMaximumNeoLoadVersion() {
		return Optional.absent();
	}
}
