import java.util.List;

public interface UserRep <T>{
    List<T> findAllByAge(Integer age);
    List<T> findAll();

}
