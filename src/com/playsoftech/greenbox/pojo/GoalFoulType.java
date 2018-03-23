package com.playsoftech.greenbox.pojo;

public enum GoalFoulType {
	FH
	{
		public String toString()
		{
			return "FH";
		}
	},
	SH
	{
		public String toString()
		{
			return "SH";
		}
	},
	PENALTY
	{
		public String toString()
		{
			return "PENALTY";
		}
	},
	GOLDEN
	{
		public String toString()
		{
			return "GOLDEN";
		}
	},
	OG
	{
		public String toString()
		{
			return "OG";
		}
	};
	public abstract String toString();
}
