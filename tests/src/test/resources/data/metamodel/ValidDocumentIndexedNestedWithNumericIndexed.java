package valid;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import lombok.*;
import org.springframework.data.annotation.Id;
import valid.AddressWithNumericIndexed;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Document("tst")
public class ValidDocumentIndexedNestedWithNumericIndexed {
  @Id
  private String id;

  @NonNull
  @Indexed
  private AddressWithNumericIndexed address;
}