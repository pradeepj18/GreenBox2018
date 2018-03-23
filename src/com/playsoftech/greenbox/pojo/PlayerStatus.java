package com.playsoftech.greenbox.pojo;

public enum PlayerStatus {
	
	NOTPLAYED
	{
		public String toString()
		{
			return "NOTPLAYED";
		}
	},
	APPEARING
	{
		public String toString()
		{
			return "APPEARING";
		}
	},
	INJURED
	{
		public String toString()
		{
			return "INJURED";
		}
	},
	PLAYED
	{
		public String toString()
		{
			return "PLAYED";
		}
	};
	public abstract String toString();
}
