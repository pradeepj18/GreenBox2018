package com.playsoftech.greenbox.pojo;

public enum Gender {
	MALE
	{
		public String toString()
		{
			return "MALE";
		}
	},
	FEMALE
	{
		public String toString()
		{
			return "FEMALE";
		}
	};
	public abstract String toString();
}
