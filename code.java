import java.util.*;

public class TeacherArrangement {
	static Scanner scn = new Scanner(System.in);
	//no of periods = 8
	public static int t = 8;

	public static void main(String[] args) {
	    System.out.println("Enter no. of teachers in a school");
		int n = scn.nextInt();
		TeacherRecord rec = new TeacherRecord();
		for (int i = 0; i < n; i++) {
			System.out.println("Enter Name and Subject Stream");
			String name = new String();
			name = scn.next();
			char SubStream = scn.next().charAt(0);
			rec.takeInput(name, SubStream);
		}
		
		
		System.out.println("Enter no. of absent");
		int noA = scn.nextInt();
		String[] str = new String[noA];

		for (int i = 0; i < noA; i++) {
			str[i] = scn.next();
			rec.changeIsPresent(str[i]);
		}
		for (int i = 0; i < noA; i++) {
			rec.getArrangements(str[i]);
		}
	}

	static class TeacherRecord {
		class teacher {
			String name;
			boolean[] isFree;
			char SubStream;
			boolean isPresent;
			int noOfLect = 0;
		}

		HashMap<String, teacher> map = new HashMap<>();

		public void takeInput(String name, char ch) {
			takeinput(name, ch);
		}

		public void changeIsPresent(String string) {
			teacher node = map.get(string);
			node.isPresent = false;
		}

		private void takeinput(String nm, char ch) {
			teacher node = new teacher();
			node.name = nm;
			node.isPresent = true;
			node.isFree = new boolean[8];
			node.SubStream = ch;
			System.out.println("Enter period Schedule");
			System.out.println("Enter no. of lectures");
		int nol = scn.nextInt();
			for (int i = 0; i < t; i++) {
				node.isFree[i] = true;
			}
                   System.out.println("Enter"+nol+" lecture period number");
			for (int i = 0; i < nol; i++) {
				int period = scn.nextInt();
				node.isFree[period] = false;
				node.noOfLect++;
			}
			map.put(nm, node);
		}

		public void getArrangements(String string) {
			teacher node = map.get(string);
			char ch = node.SubStream;
			boolean[] arr = node.isFree;
			for (int i = 0; i < t; i++) {
				if (arr[i] == false) {
					System.out.println(string + "\t=>\t" + makeArrangement(i, ch) +"\tPERIOD NO.\t" + i);
				}
			}
		}

		private String makeArrangement(int period, char ch) {
			Iterator mapIterator = map.entrySet().iterator();
			ArrayList<teacher> teacherArr = new ArrayList<>();
			int min = 99;
			while (mapIterator.hasNext()) {
				Map.Entry mapElement = (Map.Entry) mapIterator.next();
				teacher node = (teacher) mapElement.getValue();
				if (node.isFree[period] == true && node.SubStream == ch && node.noOfLect < t/2 && node.isPresent == true) {
					teacherArr.add(node);
				}
			}
			ArrayList<String> minTeacher = noOfmin(teacherArr);
			Random r = new Random();
			int randomNumber = r.nextInt(minTeacher.size());
			String substitute =  minTeacher.get(randomNumber);
			teacher node = map.get(substitute);
			node.isFree[period] = false;
			node.noOfLect++;
			return substitute;
		}

		private ArrayList<String> noOfmin(ArrayList<teacher> teacherArr) {
			int min = 99;
			int k = 0;
			ArrayList<String> arr = new ArrayList<>();
			while (k < teacherArr.size()) {
				int prd = teacherArr.get(k).noOfLect;
				if (prd < min) {
					min = prd;
				}
				k++;
			}
			k = 0;
			while (k < teacherArr.size()) {
				int prd = teacherArr.get(k).noOfLect;
				if (prd == min) {
					arr.add(teacherArr.get(k).name);
				}
				k++;
			}

			return arr;
		}

	}
}
