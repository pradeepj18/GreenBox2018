package com.playsoftech.greenbox.pojo;


public enum CardName {
	RED
	{
		public String toString()
		{
			return "RED";
		}
	},
	YELLOW
	{
		public String toString()
		{
			return "YELLOW";
		}
	};
	public abstract String toString();
}
