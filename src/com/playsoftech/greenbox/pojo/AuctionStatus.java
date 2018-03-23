package com.playsoftech.greenbox.pojo;

public enum AuctionStatus {
	UNSELECTED
	{
		public String toString()
		{
			return "UNSELECTED";
		}
	},
	SELECTED
	{
		public String toString()
		{
			return "SELECTED";
		}
	};
	public abstract String toString();
}
