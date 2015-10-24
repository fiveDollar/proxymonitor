package com.windoor.getwebcon;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetWebcon getwcon = new GetWebcon();
		String con =getwcon.getwebcon("http://www.tkdaili.com/api/getiplist.aspx?vkey=C9903F9343C7D7A26D285495512366C9&num=10&high=1&style=3");
		System.out.println(con);
	}

}
