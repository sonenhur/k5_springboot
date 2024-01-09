# @Autowired
Spring은 IoC (Inversion of Control) 컨테이너를 제공하여 객체 간의 의존성을 관리하는데, @Autowired 어노테이션은 이러한 의존성 주입을 간편하게 처리할 수 있도록 도와줍니다.
```java
public class MemberServiceTest {
  @Autowired
  **private** MemberService memberService;
  }
```
MemberServiceTest 클래스의 멤버 변수인 memberService에 @Autowired 어노테이션이 붙어있습니다. 이는 해당 멤버 변수가 Spring IoC 컨테이너에서 자동으로 MemberService 타입의 빈(Bean)을 찾아서 주입하라는 의미입니다. 즉, 개발자가 직접 객체를 생성하거나 주입할 필요 없이 Spring이 적절한 빈을 찾아서 주입해줍니다.

# 빈(Bean)
빈을 등록하기 위해서는 해당 클래스에 **`@Component`**, **`@Service`**, **`@Repository`**, **`@Controller`** 등의 어노테이션 중 하나를 붙일 수 있습니다.
예를 들어:
```java
@Component
public class MyService {
    // 클래스 내용
}
```
위와 같이 **`@Component`** 어노테이션을 사용하면, **`MyService`** 클래스는 Spring IoC 컨테이너에서 빈으로 등록되어 관리됩니다. 이후에 다른 클래스에서 **`@Autowired`** 어노테이션을 사용하여 이 빈을 주입받을 수 있습니다.  
