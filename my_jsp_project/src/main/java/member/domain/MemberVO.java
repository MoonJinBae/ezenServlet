package member.domain;

public class MemberVO {
	private String id;
	private String pw;
	private String email;
	private int age;
	private String joinDate;
	private String lastLogin;
	
	public MemberVO() {}

	// 회원가입
	public MemberVO(String id, String pw, String email, int age) {
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.age = age;
	}

	// 로그인
	public MemberVO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	
	
	// 회원정보 수정
	public MemberVO(String id, String pw, String email) {
		this.id = id;
		this.pw = pw;
		this.email = email;
	}

	public MemberVO(String id, String pw, String email, int age, String joinDate, String lastLogin) {
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.age = age;
		this.joinDate = joinDate;
		this.lastLogin = lastLogin;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", email=" + email + ", age=" + age + ", joinDate=" + joinDate
				+ ", lastLogin=" + lastLogin + "]";
	}
	
	
}
