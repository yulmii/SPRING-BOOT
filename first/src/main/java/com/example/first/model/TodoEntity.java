package com.example.first.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Builder
@NoArgsConstructor // 디폴트 생성자 매개변수 없는 생성자
@AllArgsConstructor
@Data
@Entity // Entity에 이름을 부여하고 싶다면 @Entity("TodoEntity")처럼 매개변수를 넣어줄 수 있다
@Table(name = "Todo") // 이 엔티티는 데이터베이스의 Todo테이블에 매핑 된다는 것이다. //만약 @Table을 추가하지 않거나 추가해도 name을 명시하지 않는다면 @Entity의 이름을 테이블 이름으로 간주한다 또 @Entity에 이름을 지정하지 않는 경우 클래스의 이름을 테이블 이름으로 간주한다.
public class TodoEntity {
	@Id // id가 기본 키 이므로 필드위에 @Id
	@GeneratedValue(generator="system-uuid") //id를 자동생성하겠다는 뜻 이때 매개변수인 generator로 어떤방식으로 id를 생성할지 지정. p.127
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id; // 이 오브젝트의 아이디
	private String userId; // 이 오브젝트르 생성한 사용자의 아이디
	private String title; // Todo 타이틀 (예 : 운동하기)
	private boolean done; // true - todo를 완료한 경우(checked)
}