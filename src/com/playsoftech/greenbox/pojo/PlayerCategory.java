package com.playsoftech.greenbox.pojo;

public enum PlayerCategory {
	CAPTAIN
	{
		public String toString()
		{
			return "CAPTAIN";
		}
	},
	GOALKEEPER
	{
		public String toString()
		{
			return "GOALKEEPER";
		}
	},
	STRIKER
	{
		public String toString()
		{
			return "STRIKER";
		}
	},
	DEFENDER
	{
		public String toString()
		{
			return "DEFENDER";
		}
	},
	MIDFIELDER
	{
		public String toString()
		{
			return "MIDFIELDER";
		}
	},
	PLAYER
	{
		public String toString()
		{
			return "PLAYER";
		}
	},
	EXTRA
	{
		public String toString()
		{
			return "EXTRA";
		}
	};
	public abstract String toString();
}
