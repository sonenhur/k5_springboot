# Spring IoC와 @Autowired 어노테이션
Spring은 IoC (Inversion of Control) 컨테이너로 객체 간의 의존성을 관리하며, 이를 편리하게 처리하기 위해 @Autowired 어노테이션을 제공.

```java
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;
}
```
@Autowired 어노테이션이 MemberServiceTest 클래스의 memberService 멤버 변수에 사용됨.  
이는 Spring IoC 컨테이너에서 자동으로 MemberService 타입의 빈을 찾아서 주입하라는 의미.  
개발자가 직접 객체를 생성하거나 주입할 필요 없이 Spring이 적절한 빈을 찾아서 주입함.  

## 빈(Bean) 등록
빈을 등록하기 위해서는 해당 클래스에 다양한 어노테이션 중 하나를 사용.  

### 1. @Component
가장 일반적인 어노테이션으로, 어떠한 계층 구조에도 속하지 않는 일반적인 Spring 빈을 나타냄. 주로 재사용 가능한 컴포넌트에 사용.  

```java
@Component
public class MyComponent {
    // 클래스 내용
}
```
### 2. @Service
비즈니스 로직이나 서비스 계층에 해당하는 빈에 사용. 주로 서비스 클래스에서 사용되며, @Component의 특화된 형태로 간주됨.  

```java
@Service
public class MyService {
    // 클래스 내용
}
```
### 3. @Repository
주로 데이터베이스와 관련된 작업을 수행하는 DAO(Data Access Object) 클래스에 사용. 데이터베이스 예외를 Spring의 DataAccessException으로 변환함.  

```java
@Repository
public class MyRepository {
    // 클래스 내용
}
```
### 4. @Controller
주로 Spring MVC 웹 애플리케이션에서 컨트롤러 역할을 하는 클래스에 사용. 사용자의 요청을 처리하고 적절한 응답을 반환하는데 사용됨.  

```java
@Controller
public class MyController {
    // 클래스 내용
}
```
이러한 어노테이션들은 코드를 논리적으로 그룹화하고, Spring IoC 컨테이너에서 빈으로 등록되어 관리되도록 돕는다.  
