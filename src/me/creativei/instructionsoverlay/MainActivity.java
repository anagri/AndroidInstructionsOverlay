package me.creativei.instructionsoverlay;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

	private static final String PREF_FIRSTLAUNCH_HELP = "helpDisplayed";
	private boolean helpDisplayed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		showHelpForFirstLaunch();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_help) {
			showHelp();
		}
		return super.onOptionsItemSelected(item);
	}

	private void showHelpForFirstLaunch() {
		if (helpDisplayed)
			return;
		helpDisplayed = getPreferenceValue(PREF_FIRSTLAUNCH_HELP, false);
		if (!helpDisplayed) {
			savePreference(PREF_FIRSTLAUNCH_HELP, true);
			showHelp();
		}
	}

	private boolean getPreferenceValue(String key, boolean defaultValue) {
		SharedPreferences preferences = getPreferences(MODE_PRIVATE);
		return preferences.getBoolean(key, defaultValue);
	}

	private void savePreference(String key, boolean value) {
		SharedPreferences preferences = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	private void showHelp() {
		final View instructionsContainer = findViewById(R.id.container_help);
		instructionsContainer.setVisibility(View.VISIBLE);
		instructionsContainer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				instructionsContainer.setVisibility(View.INVISIBLE);
			}
		});
	}
}
