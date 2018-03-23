package com.playsoftech.greenbox.pojo;

public enum CardType {
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
	GOLDEN
	{
		public String toString()
		{
			return "GOLDEN";
		}
	},
	PENALTY
	{
		public String toString()
		{
			return "PENALTY";
		}
	};
	public abstract String toString();
}
