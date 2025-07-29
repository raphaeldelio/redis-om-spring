package valid;

import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.NumericIndexed;
import com.redis.om.spring.annotations.Searchable;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class AddressWithNumericIndexed {
  @NonNull
  @Indexed
  private String city;

  @NonNull
  @Searchable(nostem = true)
  private String street;

  @NonNull
  @NumericIndexed(sortable = true)
  private Integer zipCode;

  @NonNull
  @NumericIndexed(alias = "lat")
  private Double latitude;

  @NonNull
  @NumericIndexed(alias = "lng", sortable = true)
  private Double longitude;
}