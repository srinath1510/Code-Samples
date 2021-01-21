// --== CS400 File Header Information ==--
// Name: Srinath Srinivasan
// Email: srinivasan32@wisc.edu
// Team: NG
// TA: Daniel Finer
// Lecturer: Gary Dahl
// Notes to Grader: My part of the project.

import java.util.Arrays;

/*
 * This is the Backend class of the School District Database project.
 */
public class BackEndDeveloper {

  private static SearchRBTree sTree = new SearchRBTree(); // student tree
  private static SearchRBTree fTree = new SearchRBTree(); // faculty tree

  /**
   * This method adds a student to the student red black tree.
   * 
   * @param facultyOrStudent
   * @param firstName
   * @param lastName
   * @param age
   * @param grade
   * @param school
   */
  public void addStudent(char facultyOrStudent, String firstName, String lastName, int age,
      int grade, char school) {
    KeyValue student = new KeyValue(facultyOrStudent, firstName, lastName, age, grade, school);
    try {
      sTree.insert(student); // insert student into student red black tree.
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(); // throw exception
    }
    return;
  }

  /**
   * 
   * This method adds a faculty member to the faculty red black tree.
   * 
   * @param facultyOrStudent
   * @param firstName
   * @param lastName
   * @param age
   * @param grade
   * @param subject
   * @param school
   */
  public void addFaculty(char facultyOrStudent, String firstName, String lastName, int age,
      int grade, String subject, char school) {
    KeyValue faculty =
        new KeyValue(facultyOrStudent, firstName, lastName, age, grade, subject, school);
    try {
      fTree.insert(faculty);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException();
    }
    return;
  }

  /**
   * This is a helper method that calculates the grade level of a student object using its age.
   * 
   * @param age
   * @return int
   */
  public int calculateGradeLevel(int age) {
    int grade = 0;;
    if (age == 6) {
      grade = 1;
    }
    if (age == 7) {
      grade = 2;
    }
    if (age == 8) {
      grade = 3;
    }
    if (age == 9) {
      grade = 4;
    }
    if (age == 10) {
      grade = 5;
    }
    if (age == 11) {
      grade = 6;
    }
    if (age == 12) {
      grade = 7;
    }
    if (age == 13) {
      grade = 8;
    }
    if (age == 14) {
      grade = 9;
    }
    if (age == 15) {
      grade = 10;
    }
    if (age == 16) {
      grade = 11;
    }
    if (age == 17) {
      grade = 12;
    }
    return grade;

  }

  /**
   * This is a helper method that calculates the school level of a student object using its grade.
   * 
   * @param grade
   * @return char
   */
  public char calculateSchool(int grade) {
    char school;
    if (grade <= 5) {
      school = 'E';
    } else if (grade == 6 || grade == 7 || grade == 8) {
      school = 'M';
    } else {
      school = 'H';
    }
    return school;

  }

  /**
   * This method clears the student red black tree.
   */
  public void clearStudents() {
    sTree = new SearchRBTree(); // assigns student tree to new red black tree.
  }

  /**
   * This method clears the faculty red black tree.
   */
  public void clearFaculty() {
    fTree = new SearchRBTree(); // assigns faculty tree to new red black tree.
  }

  /**
   * This method lists all the students in the student red black tree.
   * 
   * @return PersonAtSchool[]
   */
  public PersonAtSchool[] listStudents() {
    KeyValue[] kArr = sTree.list(); // calls list method from searchRBTree class.
    if (kArr == null) {
      return new PersonAtSchool[0];
    }
    PersonAtSchool[] pArr = new PersonAtSchool[kArr.length];
    for (int i = 0; i < kArr.length; i++) { // loop to add objects to array.
      pArr[i] = kArr[i].getValue(); // gets value from KeyValue.
    }
    return pArr;

  }

  /**
   * This method lists all the faculty members in the faculty red black tree.
   * 
   * @return PersonAtSchool[]
   */
  public PersonAtSchool[] listFaculty() {
    KeyValue[] kArr = fTree.list();
    if (kArr == null) {
      return new PersonAtSchool[0];
    }
    PersonAtSchool[] pArr = new PersonAtSchool[kArr.length];

    for (int i = 0; i < kArr.length; i++) {
      pArr[i] = kArr[i].getValue();
    }
    return pArr;

  }

  /**
   * This method finds and returns the student object to be returned which is specified by the
   * lastName.
   * 
   * @param lastName
   * @return PersonAtSchool
   */
  public PersonAtSchool searchStudent(String lastName) {
    PersonAtSchool student = sTree.search(lastName); // calls search() method from SearchRBTree
                                                     // class.
    return student;

  }

  /**
   * This method finds and returns the faculty object to be returned which is specified by the
   * lastName.
   * 
   * @param lastName
   * @return PersonAtSchool
   */
  public PersonAtSchool searchFaculty(String lastName) {
    PersonAtSchool faculty = fTree.search(lastName);
    return faculty;

  }

}
