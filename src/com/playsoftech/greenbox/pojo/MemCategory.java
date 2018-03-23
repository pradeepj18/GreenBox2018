package com.playsoftech.greenbox.pojo;

public enum MemCategory {

	PLAYER
	{
		public String toString()
		{
			return "PLAYER";
		}
	},
	OFFICIAL
	{
		public String toString()
		{
			return "OFFICIAL";
		}
	};
	public abstract String toString();
}
