package ru.coc.flashback.dto.api.clan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 04.01.2019.
 */

@Data
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@JsonIgnoreProperties({"paging"})
public class ItemsApiDto {

    private List<ClanMemberApiDto> items;
}
