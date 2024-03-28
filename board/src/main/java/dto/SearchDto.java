package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {

  private String criteria;
  private String keyword;
  private int page;
  private int amount;

  public SearchDto(int page, int amount) {
    this.page = page;
    this.amount = amount;
  }
}
