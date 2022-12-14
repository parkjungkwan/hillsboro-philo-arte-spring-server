package hillsboro.philoarte.scalar.pagers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Builder
@AllArgsConstructor
@Data
public class FundingPager<Dto, EN> {
    private List<Dto> dtoList;
    private int totalPage;
    private int page;
    private int PerPage;
    private int start, end;
    private boolean prev, next;
    private List<Integer> pageList;

    public FundingPager(Page<EN> result, Function<EN, Dto> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1;
        this.PerPage = pageable.getPageSize();
        // temp end page
        int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;
        start = tempEnd - 9;
        prev = start > 1;
        end = totalPage > tempEnd ? tempEnd : totalPage;
        next = totalPage > tempEnd;
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

}
