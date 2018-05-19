public class HammingDistance {

	public static int getHD(String generated, String objective) {
		int distance = 0;
		if (generated.length() != objective.length()) {
			String error = "Los Strings no son del mismo largo";
			throw new IllegalArgumentException(error);
		}
		for (int i = generated.length() - 1; i >= 0; i--) {
			if (generated.charAt(i) != objective.charAt(i)) {
				distance++;
			}
		}
		return distance;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getHD("00001000", "11101110"));
	}

}
