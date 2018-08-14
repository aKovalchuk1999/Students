/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOMAIN;

public class Student implements java.io.Serializable{
        private int studentId;
	private String lastName;
	private String firstName;
	private String foreName;
	private String zalikCardNumber;
	private String studyForm;
	private String studyType;
        private String birthDate;
	private String sex;
	private String address;
	private String phone;
        private String groupName;
        private String fotoUrl;
        private byte[] foto;
        private Integer studCount;
        
        
        
    public Student(int id, String lastName, String firstName, String foreName, String groupName, String zalikCardNumber, String studyForm, String studyType, String birthDate, String sex, String address, String phone, byte[] foto, String fotoUrl) {
        this.studentId = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.foreName = foreName;
        this.groupName = groupName;
        this.zalikCardNumber = zalikCardNumber;
        this.studyForm = studyForm;
        this.studyType = studyType;
        this.birthDate = birthDate;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
        this.foto = foto;
        this.fotoUrl = fotoUrl;
    }
    
    public Student(String gpName, int _studCount){
        this.groupName = gpName;
        this.studCount = _studCount;
    }
    
        public int getStudentId() {
            return studentId;
        }
        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getForeName() {
		return foreName;
	}
	public void setForeName(String foreName) {
		this.foreName = foreName;
	}

	public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

	public String getStudyType() {
		return studyType;
	}
	public void setStudyType(String studyType) {
		this.studyType = studyType;
	}

	public String getZalikCardNumber() {
		return zalikCardNumber;
	}
	public void setZalikCardNumber(String zalikCardNumber) {
		this.zalikCardNumber = zalikCardNumber;
	}

	public String getStudyForm() {
		return studyForm;
	}
	public void setStudyForm(String studyForm) {
		this.studyForm = studyForm;
	}

	public String getBirthdate() {
                return birthDate;
	}

	public void setBirthdate(String birthDate) {
            this.birthDate = birthDate;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
        
        public byte[] getFoto() {
            return foto;
        }
        public void setFoto(byte[] foto) {
            this.foto = foto;
        }
        
        public String getFotoUrl() {
            return fotoUrl;
        }
        public void setFotoUrl(String fotoUrl) {
            this.fotoUrl = fotoUrl;
        }
        
        public Integer getStudCount() {
            return studCount;
        }
        public void setStudCount(Integer studCount) {
            this.studCount = studCount;
        }
}
