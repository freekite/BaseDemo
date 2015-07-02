package com.demo.basedemo.activity;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.os.Bundle;
import android.view.View;

import com.demo.basedemo.R;
import com.demo.basedemo.base.BaseActivity;

public class VideoActivity extends BaseActivity{
	
//	private String path = "http://www.modrails.com/videos/passenger_nginx.mov";
	private String path = "http://p.bokecc.com/flash/single/97BBB0FDEF9024F9_853572CFDB8818729C33DC5901307461_false_352AB1EEF45632EF_1/player.swf";
	private String path2 = "http://tj.ctfs.ftn.qq.com/ftn_handler/bedc4b4a11c16656545912a912c70389791e7c91d703303ce8a02d0de8f5b862684175cb01c9a9b09a657a06c7b94238e31f3be31601e56535a94a7a776cc60f/20150630223259.mkv";
	private VideoView mVideoView;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.activity_video);
		mVideoView=(VideoView) findViewById(R.id.vv_video);
		mVideoView.setVideoPath(path);
		mVideoView.setMediaController(new MediaController(this));
		mVideoView.requestFocus();
		mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mediaPlayer) {
				mediaPlayer.setPlaybackSpeed(1.0f);
			}
		});
	}
	
	public void openVideo(View v){
		mVideoView.setVideoPath(path2);
	}

}
