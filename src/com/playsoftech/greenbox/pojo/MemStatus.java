package com.playsoftech.greenbox.pojo;

public enum MemStatus {
	
	OLD
	{
		public String toString()
		{
			return "OLD";
		}
	},
	NEW
	{
		public String toString()
		{
			return "NEW";
		}
	};
	public abstract String toString();
}
