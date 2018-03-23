package com.playsoftech.greenbox.pojo;

public enum MatchStatus {
	ONGOING
	{
		public String toString()
		{
			return "ONGOING";
		}
	},
	UPCOMING
	{
		public String toString()
		{
			return "UPCOMING";
		}
	},
	DONE
	{
		public String toString()
		{
			return "DONE";
		}
	},
	ABANDONED
	{
		public String toString()
		{
			return "ABANDONED";
		}
	};
	public abstract String toString();
	
}
