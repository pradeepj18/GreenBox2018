package com.playsoftech.greenbox.pojo;

public enum TourAge {
	SENIOR
	{
		public String toString()
		{
			return "SENIOR";
		}
	},
	UNDER19
	{
		public String toString()
		{
			return "UNDER19";
		}
	},
	UNDER17
	{
		public String toString()
		{
			return "UNDER17";
		}
	},
	UNDER14
	{
		public String toString()
		{
			return "UNDER14";
		}
	},
	UNDER12
	{
		public String toString()
		{
			return "UNDER12";
		}
	},
	UNDER11
	{
		public String toString()
		{
			return "UNDER11";
		}
	},
	JUNIOR
	{
		public String toString()
		{
			return "JUNIOR";
		}
	},
	OPEN
	{
		public String toString()
		{
			return "OPEN";
		}
	};
	public abstract String toString();
}
