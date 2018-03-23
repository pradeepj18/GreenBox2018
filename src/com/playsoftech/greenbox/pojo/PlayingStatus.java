package com.playsoftech.greenbox.pojo;

public enum PlayingStatus {
	ACTIVE
	{
		public String toString()
		{
			return "ACTIVE";
		}
	},
	BANNED
	{
		public String toString()
		{
			return "BANNED";
		}
	};
	public abstract String toString();
}
