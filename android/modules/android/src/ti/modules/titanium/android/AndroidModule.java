/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */
package ti.modules.titanium.android;


import org.appcelerator.kroll.KrollInvocation;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiContext;
import org.appcelerator.titanium.proxy.IntentProxy;
import org.appcelerator.titanium.proxy.RProxy;

import android.app.Activity;
import android.content.Intent;

@Kroll.module
public class AndroidModule extends KrollModule
{
	private static final String TAG = "TiAndroid";

	@Kroll.constant public static final String ACTION_AIRPLANE_MODE_CHANGED = Intent.ACTION_AIRPLANE_MODE_CHANGED;
	@Kroll.constant public static final String ACTION_ALL_APPS = Intent.ACTION_ALL_APPS;
	@Kroll.constant public static final String ACTION_ANSWER = Intent.ACTION_ANSWER;
	@Kroll.constant public static final String ACTION_ATTACH_DATA = Intent.ACTION_ATTACH_DATA;
	@Kroll.constant public static final String ACTION_BATTERY_CHANGED = Intent.ACTION_BATTERY_CHANGED;
	@Kroll.constant public static final String ACTION_BATTERY_LOW = Intent.ACTION_BATTERY_LOW;
	@Kroll.constant public static final String ACTION_BATTERY_OKAY = Intent.ACTION_BATTERY_OKAY;
	@Kroll.constant public static final String ACTION_BOOT_COMPLETED = Intent.ACTION_BOOT_COMPLETED;
	@Kroll.constant public static final String ACTION_BUG_REPORT = Intent.ACTION_BUG_REPORT;
	@Kroll.constant public static final String ACTION_CALL = Intent.ACTION_CALL;
	@Kroll.constant public static final String ACTION_CALL_BUTTON = Intent.ACTION_CALL_BUTTON;
	@Kroll.constant public static final String ACTION_CAMERA_BUTTON = Intent.ACTION_CAMERA_BUTTON;
	@Kroll.constant public static final String ACTION_CHOOSER = Intent.ACTION_CHOOSER;
	@Kroll.constant public static final String ACTION_CLOSE_SYSTEM_DIALOGS = Intent.ACTION_CLOSE_SYSTEM_DIALOGS;
	@Kroll.constant public static final String ACTION_CONFIGURATION_CHANGED = Intent.ACTION_CONFIGURATION_CHANGED;
	@Kroll.constant public static final String ACTION_CREATE_SHORTCUT = Intent.ACTION_CREATE_SHORTCUT;
	@Kroll.constant public static final String ACTION_DATE_CHANGED = Intent.ACTION_DATE_CHANGED;
	@Kroll.constant public static final String ACTION_DEFAULT = Intent.ACTION_DEFAULT;
	@Kroll.constant public static final String ACTION_DELETE = Intent.ACTION_DELETE;
	@Kroll.constant public static final String ACTION_DEVICE_STORAGE_LOW = Intent.ACTION_DEVICE_STORAGE_LOW;
	@Kroll.constant public static final String ACTION_DIAL = Intent.ACTION_DIAL;
	@Kroll.constant public static final String ACTION_EDIT = Intent.ACTION_EDIT;
	@Kroll.constant public static final String ACTION_GET_CONTENT = Intent.ACTION_GET_CONTENT;
	@Kroll.constant public static final String ACTION_GTALK_SERVICE_CONNECTED = Intent.ACTION_GTALK_SERVICE_CONNECTED;
	@Kroll.constant public static final String ACTION_GTALK_SERVICE_DISCONNECTED = Intent.ACTION_GTALK_SERVICE_DISCONNECTED;
	@Kroll.constant public static final String ACTION_HEADSET_PLUG = Intent.ACTION_HEADSET_PLUG;
	@Kroll.constant public static final String ACTION_INPUT_METHOD_CHANGED = Intent.ACTION_INPUT_METHOD_CHANGED;
	@Kroll.constant public static final String ACTION_INSERT = Intent.ACTION_INSERT;
	@Kroll.constant public static final String ACTION_INSERT_OR_EDIT = Intent.ACTION_INSERT_OR_EDIT;
	@Kroll.constant public static final String ACTION_MAIN = Intent.ACTION_MAIN;
	@Kroll.constant public static final String ACTION_MANAGE_PACKAGE_STORAGE = Intent.ACTION_MANAGE_PACKAGE_STORAGE;
	@Kroll.constant public static final String ACTION_MEDIA_BAD_REMOVAL = Intent.ACTION_MEDIA_BAD_REMOVAL;
	@Kroll.constant public static final String ACTION_MEDIA_BUTTON = Intent.ACTION_MEDIA_BUTTON;
	@Kroll.constant public static final String ACTION_MEDIA_CHECKING = Intent.ACTION_MEDIA_CHECKING;
	@Kroll.constant public static final String ACTION_MEDIA_EJECT = Intent.ACTION_MEDIA_EJECT;
	@Kroll.constant public static final String ACTION_MEDIA_MOUNTED = Intent.ACTION_MEDIA_MOUNTED;
	@Kroll.constant public static final String ACTION_MEDIA_NOFS = Intent.ACTION_MEDIA_NOFS;
	@Kroll.constant public static final String ACTION_MEDIA_REMOVED = Intent.ACTION_MEDIA_REMOVED;
	@Kroll.constant public static final String ACTION_MEDIA_SCANNER_FINISHED = Intent.ACTION_MEDIA_SCANNER_FINISHED;
	@Kroll.constant public static final String ACTION_MEDIA_SCANNER_SCAN_FILE = Intent.ACTION_MEDIA_SCANNER_SCAN_FILE;
	@Kroll.constant public static final String ACTION_MEDIA_SCANNER_STARTED = Intent.ACTION_MEDIA_SCANNER_STARTED;
	@Kroll.constant public static final String ACTION_MEDIA_SHARED = Intent.ACTION_MEDIA_SHARED;
	@Kroll.constant public static final String ACTION_MEDIA_UNMOUNTABLE = Intent.ACTION_MEDIA_UNMOUNTABLE;
	@Kroll.constant public static final String ACTION_MEDIA_UNMOUNTED = Intent.ACTION_MEDIA_UNMOUNTED;
	@Kroll.constant public static final String ACTION_NEW_OUTGOING_CALL = Intent.ACTION_NEW_OUTGOING_CALL;
	@Kroll.constant public static final String ACTION_PACKAGE_ADDED = Intent.ACTION_PACKAGE_ADDED;
	@Kroll.constant public static final String ACTION_PACKAGE_CHANGED = Intent.ACTION_PACKAGE_CHANGED;
	@Kroll.constant public static final String ACTION_PACKAGE_DATA_CLEARED = Intent.ACTION_PACKAGE_DATA_CLEARED;
	@Kroll.constant public static final String ACTION_PACKAGE_INSTALL = Intent.ACTION_PACKAGE_INSTALL;
	@Kroll.constant public static final String ACTION_PACKAGE_REMOVED = Intent.ACTION_PACKAGE_REMOVED;
	@Kroll.constant public static final String ACTION_PACKAGE_REPLACED = Intent.ACTION_PACKAGE_REPLACED;
	@Kroll.constant public static final String ACTION_PACKAGE_RESTARTED = Intent.ACTION_PACKAGE_RESTARTED;
	@Kroll.constant public static final String ACTION_PICK = Intent.ACTION_PICK;
	@Kroll.constant public static final String ACTION_PICK_ACTIVITY = Intent.ACTION_PICK_ACTIVITY;
	@Kroll.constant public static final String ACTION_POWER_CONNECTED = Intent.ACTION_POWER_CONNECTED;
	@Kroll.constant public static final String ACTION_POWER_DISCONNECTED = Intent.ACTION_POWER_DISCONNECTED;
	@Kroll.constant public static final String ACTION_POWER_USAGE_SUMMARY = Intent.ACTION_POWER_USAGE_SUMMARY;
	@Kroll.constant public static final String ACTION_PROVIDER_CHANGED = Intent.ACTION_PROVIDER_CHANGED;
	@Kroll.constant public static final String ACTION_REBOOT = Intent.ACTION_REBOOT;
	@Kroll.constant public static final String ACTION_RUN = Intent.ACTION_RUN;
	@Kroll.constant public static final String ACTION_SCREEN_OFF = Intent.ACTION_SCREEN_OFF;
	@Kroll.constant public static final String ACTION_SCREEN_ON = Intent.ACTION_SCREEN_ON;
	@Kroll.constant public static final String ACTION_SEARCH = Intent.ACTION_SEARCH;
	@Kroll.constant public static final String ACTION_SEARCH_LONG_PRESS = Intent.ACTION_SEARCH_LONG_PRESS;
	@Kroll.constant public static final String ACTION_SEND = Intent.ACTION_SEND;
	@Kroll.constant public static final String ACTION_SENDTO = Intent.ACTION_SENDTO;
	@Kroll.constant public static final String ACTION_SEND_MULTIPLE = Intent.ACTION_SEND_MULTIPLE;
	@Kroll.constant public static final String ACTION_SET_WALLPAPER = Intent.ACTION_SET_WALLPAPER;
	@Kroll.constant public static final String ACTION_SHUTDOWN = Intent.ACTION_SHUTDOWN;
	@Kroll.constant public static final String ACTION_SYNC = Intent.ACTION_SYNC;
	@Kroll.constant public static final String ACTION_SYSTEM_TUTORIAL = Intent.ACTION_SYSTEM_TUTORIAL;
	@Kroll.constant public static final String ACTION_TIME_CHANGED = Intent.ACTION_TIME_CHANGED;
	@Kroll.constant public static final String ACTION_TIME_TICK = Intent.ACTION_TIME_TICK;
	@Kroll.constant public static final String ACTION_UID_REMOVED = Intent.ACTION_UID_REMOVED;
	@Kroll.constant public static final String ACTION_UMS_CONNECTED = Intent.ACTION_UMS_CONNECTED;
	@Kroll.constant public static final String ACTION_UMS_DISCONNECTED = Intent.ACTION_UMS_DISCONNECTED;
	@Kroll.constant public static final String ACTION_USER_PRESENT = Intent.ACTION_USER_PRESENT;
	@Kroll.constant public static final String ACTION_VIEW = Intent.ACTION_VIEW;
	@Kroll.constant public static final String ACTION_VOICE_COMMAND = Intent.ACTION_VOICE_COMMAND;
	@Kroll.constant public static final String ACTION_WALLPAPER_CHANGED = Intent.ACTION_WALLPAPER_CHANGED;
	@Kroll.constant public static final String ACTION_WEB_SEARCH = Intent.ACTION_WEB_SEARCH;

	@Kroll.constant public static final String CATEGORY_ALTERNATIVE = Intent.CATEGORY_ALTERNATIVE;
	@Kroll.constant public static final String CATEGORY_BROWSABLE = Intent.CATEGORY_BROWSABLE;
	@Kroll.constant public static final String CATEGORY_DEFAULT = Intent.CATEGORY_DEFAULT;
	@Kroll.constant public static final String CATEGORY_DEVELOPMENT_PREFERENCE = Intent.CATEGORY_DEVELOPMENT_PREFERENCE;
	@Kroll.constant public static final String CATEGORY_EMBED = Intent.CATEGORY_EMBED;
	@Kroll.constant public static final String CATEGORY_FRAMEWORK_INSTRUMENTATION_TEST = Intent.CATEGORY_FRAMEWORK_INSTRUMENTATION_TEST;
	@Kroll.constant public static final String CATEGORY_HOME = Intent.CATEGORY_HOME;
	@Kroll.constant public static final String CATEGORY_INFO = Intent.CATEGORY_INFO;
	@Kroll.constant public static final String CATEGORY_LAUNCHER = Intent.CATEGORY_LAUNCHER;
	@Kroll.constant public static final String CATEGORY_MONKEY = Intent.CATEGORY_MONKEY;
	@Kroll.constant public static final String CATEGORY_OPENABLE = Intent.CATEGORY_OPENABLE;
	@Kroll.constant public static final String CATEGORY_PREFERENCE = Intent.CATEGORY_PREFERENCE;
	@Kroll.constant public static final String CATEGORY_SAMPLE_CODE = Intent.CATEGORY_SAMPLE_CODE;
	@Kroll.constant public static final String CATEGORY_SELECTED_ALTERNATIVE = Intent.CATEGORY_SELECTED_ALTERNATIVE;
	@Kroll.constant public static final String CATEGORY_TAB = Intent.CATEGORY_TAB;
	@Kroll.constant public static final String CATEGORY_TEST = Intent.CATEGORY_TEST;
	@Kroll.constant public static final String CATEGORY_UNIT_TEST = Intent.CATEGORY_UNIT_TEST;
	
	@Kroll.constant public static final String EXTRA_ALARM_COUNT = Intent.EXTRA_ALARM_COUNT;
	@Kroll.constant public static final String EXTRA_BCC = Intent.EXTRA_BCC;
	@Kroll.constant public static final String EXTRA_CC = Intent.EXTRA_CC;
	@Kroll.constant public static final String EXTRA_DATA_REMOVED = Intent.EXTRA_DATA_REMOVED;
	@Kroll.constant public static final String EXTRA_DONT_KILL_APP = Intent.EXTRA_DONT_KILL_APP;
	@Kroll.constant public static final String EXTRA_EMAIL = Intent.EXTRA_EMAIL;
	@Kroll.constant public static final String EXTRA_INTENT = Intent.EXTRA_INTENT;
	@Kroll.constant public static final String EXTRA_KEY_EVENT = Intent.EXTRA_KEY_EVENT;
	@Kroll.constant public static final String EXTRA_PHONE_NUMBER = Intent.EXTRA_PHONE_NUMBER;
	@Kroll.constant public static final String EXTRA_REPLACING = Intent.EXTRA_REPLACING;
	@Kroll.constant public static final String EXTRA_SHORTCUT_ICON = Intent.EXTRA_SHORTCUT_ICON;
	@Kroll.constant public static final String EXTRA_SHORTCUT_ICON_RESOURCE = Intent.EXTRA_SHORTCUT_ICON_RESOURCE;
	@Kroll.constant public static final String EXTRA_SHORTCUT_INTENT = Intent.EXTRA_SHORTCUT_INTENT;
	@Kroll.constant public static final String EXTRA_SHORTCUT_NAME = Intent.EXTRA_SHORTCUT_NAME;
	@Kroll.constant public static final String EXTRA_STREAM = Intent.EXTRA_STREAM;
	@Kroll.constant public static final String EXTRA_SUBJECT = Intent.EXTRA_SUBJECT;
	@Kroll.constant public static final String EXTRA_TEMPLATE = Intent.EXTRA_TEMPLATE;
	@Kroll.constant public static final String EXTRA_TEXT = Intent.EXTRA_TEXT;
	@Kroll.constant public static final String EXTRA_TITLE = Intent.EXTRA_TITLE;
	@Kroll.constant public static final String EXTRA_UID = Intent.EXTRA_UID;

	@Kroll.constant public static final int FILL_IN_ACTION = Intent.FILL_IN_ACTION;
	@Kroll.constant public static final int FILL_IN_CATEGORIES = Intent.FILL_IN_CATEGORIES;
	@Kroll.constant public static final int FILL_IN_COMPONENT = Intent.FILL_IN_COMPONENT;
	@Kroll.constant public static final int FILL_IN_DATA = Intent.FILL_IN_DATA;
	@Kroll.constant public static final int FILL_IN_PACKAGE = Intent.FILL_IN_PACKAGE;

	@Kroll.constant public static final int FLAG_ACTIVITY_BROUGHT_TO_FRONT = Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT;
	@Kroll.constant public static final int FLAG_ACTIVITY_CLEAR_TOP = Intent.FLAG_ACTIVITY_CLEAR_TOP;
	@Kroll.constant public static final int FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET = Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
	@Kroll.constant public static final int FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS = Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS;
	@Kroll.constant public static final int FLAG_ACTIVITY_FORWARD_RESULT = Intent.FLAG_ACTIVITY_FORWARD_RESULT;
	@Kroll.constant public static final int FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY = Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY;
	@Kroll.constant public static final int FLAG_ACTIVITY_MULTIPLE_TASK = Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
	@Kroll.constant public static final int FLAG_ACTIVITY_NEW_TASK = Intent.FLAG_ACTIVITY_NEW_TASK;
	@Kroll.constant public static final int FLAG_ACTIVITY_NO_HISTORY = Intent.FLAG_ACTIVITY_NO_HISTORY;
	@Kroll.constant public static final int FLAG_ACTIVITY_NO_USER_ACTION = Intent.FLAG_ACTIVITY_NO_USER_ACTION;
	@Kroll.constant public static final int FLAG_ACTIVITY_PREVIOUS_IS_TOP = Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP;
	@Kroll.constant public static final int FLAG_ACTIVITY_REORDER_TO_FRONT = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;
	@Kroll.constant public static final int FLAG_ACTIVITY_RESET_TASK_IF_NEEDED = Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED;
	@Kroll.constant public static final int FLAG_ACTIVITY_SINGLE_TOP = Intent.FLAG_ACTIVITY_SINGLE_TOP;
	@Kroll.constant public static final int FLAG_DEBUG_LOG_RESOLUTION = Intent.FLAG_DEBUG_LOG_RESOLUTION;
	@Kroll.constant public static final int FLAG_FROM_BACKGROUND = Intent.FLAG_FROM_BACKGROUND;
	@Kroll.constant public static final int FLAG_GRANT_READ_URI_PERMISSION = Intent.FLAG_GRANT_READ_URI_PERMISSION;
	@Kroll.constant public static final int FLAG_GRANT_WRITE_URI_PERMISSION = Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
	@Kroll.constant public static final int FLAG_RECEIVER_REGISTERED_ONLY = Intent.FLAG_RECEIVER_REGISTERED_ONLY;

	@Kroll.constant public static final int URI_INTENT_SCHEME = Intent.URI_INTENT_SCHEME;
	
	@Kroll.constant public static final int PENDING_INTENT_FOR_ACTIVITY = 0;
	@Kroll.constant public static final int PENDING_INTENT_FOR_SERVICE = 1;
	@Kroll.constant public static final int PENDING_INTENT_FOR_BROADCAST = 2;
	@Kroll.constant public static final int PENDING_INTENT_MAX_VALUE = PENDING_INTENT_FOR_BROADCAST;

	@Kroll.constant public static final int RESULT_OK = Activity.RESULT_OK;
	@Kroll.constant public static final int RESULT_CANCELED = Activity.RESULT_CANCELED;
	@Kroll.constant public static final int RESULT_FIRST_USER = Activity.RESULT_FIRST_USER;
	
	protected RProxy r;
	
	public AndroidModule(TiContext tiContext) {
		super(tiContext);
	}

	@Kroll.method
	public IntentProxy createIntent(KrollInvocation invocation, Object[] args) {
		IntentProxy intent = new IntentProxy(invocation.getTiContext());
		intent.handleCreationArgs(this, args);
		return intent;
	}
	
	@Kroll.method
	public IntentProxy createIntentChooser(KrollInvocation invocation, IntentProxy target, String title) {
		return new IntentProxy(invocation.getTiContext(), Intent.createChooser(target.getIntent(), title));
	}
	
	@Kroll.getProperty(name="R")
	public RProxy getR(KrollInvocation invocation) {
		if (r == null) {
			r = new RProxy(invocation.getTiContext(), RProxy.RESOURCE_TYPE_ANDROID);
		}
		return r;
	}
}
