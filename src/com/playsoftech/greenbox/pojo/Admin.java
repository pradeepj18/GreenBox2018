package com.playsoftech.greenbox.pojo;

public enum Admin {
	YES{
		public String toString(){
			return "YES";
		}
	},
	NO{
		public String toString(){
			return "NO";
		}
	};
	public abstract String toString();
}
