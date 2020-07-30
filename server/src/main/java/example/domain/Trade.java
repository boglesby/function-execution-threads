package example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.geode.DataSerializable;
import org.apache.geode.DataSerializer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
// Note: This class is DataSerializable due to https://jira.spring.io/browse/DATAGEODE-29
public class Trade implements DataSerializable {

  @NonNull
  private Integer id;

  @NonNull
  private String cusip;

  @NonNull
  private Integer shares;

  @NonNull
  private BigDecimal price;

  @Override
  public void toData(DataOutput out) throws IOException {
    DataSerializer.writeInteger(this.id, out);
    DataSerializer.writeString(this.cusip, out);
    DataSerializer.writeInteger(this.shares, out);
    DataSerializer.writeObject(this.price, out);
  }

  @Override
  public void fromData(DataInput in) throws IOException, ClassNotFoundException {
    this.id = DataSerializer.readInteger(in);
    this.cusip = DataSerializer.readString(in);
    this.shares = DataSerializer.readInteger(in);
    this.price = DataSerializer.readObject(in);
  }
}
