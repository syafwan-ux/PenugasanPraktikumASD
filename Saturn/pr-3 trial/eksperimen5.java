class StringTest {
    public static void main(String[] args) {
        String teks1 = "Java";
        String teks2 = "Java";
	  
	  if(teks1 == teks2) //cek apakah instance teks1 = instance teks2
	  {
		System.out.println("teks1 == teks2 dengan value: "+ teks1);
  }else {
		System.out.println("teks1 =/= teks2 ");
  }

        teks1 += " Programming"; // Modifikasi 

	  if(teks1 == teks2) //cek apakah instance teks1 = instance teks2
	  {
		System.out.println("teks1 == teks2 dengan value: "+ teks1);
  }else {
		System.out.println("teks1 =/= teks2 ");
  }

        System.out.println(teks1);
        System.out.println(teks2);
    }
}
