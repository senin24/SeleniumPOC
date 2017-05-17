package com.github.senin24.selenidegridpoc.common;

public class TestData {

	private static final String[] rocks = new String[] { "KISS", "Van Halen ", "Guns 'n' Roses ", "Led Zepplin ",
			"Queen", "Slipknot ", "AC/DC ", "Metallica", "Motley Crue", "Marilyn Manson", "Judas Priest",
			"Black Sabbath", "Aerosmith", "Alice Cooper ", "Alice In Chains", "Jimi Hendrix", "Korn ", "Slayer",
			"Scorpions", "Stone Temple Pilots", "Audioslave", "Nirvana", "Rolling Stones ", "Iron Maiden ", "Disturbed",
			"Pantera ", "Deep Purple", "Rage Against The Machine", "Ozzy Osbourne", "System Of A Down ", "Tool",
			"Motorhead ", "Queensryche ", "Def Leppard", "Nine Inch Nails ", "Rush ", "WASP ", "The Who ", "Ted Nugent",
			"ZZ Top ", "Red Hot Chili Peppers ", "Pink Floyd ", "Mudvayne", "Angel ", "The Cult ", "Mercyful Fate",
			"Godsmack", "Staind ", "Linkin Park ", "Poison ", "The Darkness ", "Evanescence ", "Bon Jovi ", "Mastodon ",
			"Dio", "Velvet Revolver", "Killswitch Engage", "Lamb of God", "Cream", "Twisted Sister",
			"Avenged Sevenfold", "Saliva", "Dragonforce", "Thin Lizzy", "White Zombie", "Megadeth", "Ratt",
			"Limp Bizkit", "Whitesnake", "Deftones", "Wolfmother", "Lacuna Coil", "Heaven & Hell", "Pearl Jam", "P O D",
			"Anthrax", "Down", "AFI", "Stone Sour", "Soudgarden", "Shadowfalls", "Manowar", "Hate Breed", "Quiet Riot",
			"Janes Addiction", "A Perfect Circle", "Machine head", "Dokken", "Black Label Society", "Murderdolls",
			"Skid Row", "Krokus", "Type O Negative", "Mushroomhead", "Sevendust", "Rammstein", "Meshuggah", "Lordi",
			"Cradle Of Filth" };

	public static String [] [] getData () {		
		String[][] data = new String[rocks.length][1];
		for (int i = 0; i < rocks.length; i ++) {
			data [i] [0] = rocks [i];
		}
		return data;
	}

}
;