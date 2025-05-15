import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static util.ArgumentsProvider.getArgs;

import com.asarkar.junit.IterableConverter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import util.TimingExtension;

@ExtendWith(TimingExtension.class)
class P1621Test {
    @ParameterizedTest
    @CsvSource(delimiter = '|', textBlock = """
        [2, 3, 2, 2, 3] | 2
    """)
    @MethodSource
    @Timeout(1)
    void testNumDistinct(@ConvertWith(IterableConverter.class) List<Integer> nums, int expected) {
        assertThat(P1621.numDistinct(nums)).isEqualTo(expected);
    }

    static Stream<Arguments> testNumDistinct() {
        return getArgs("/1621.zip", br -> List.of(P1621.readIn(br)), br -> {
            try {
                return Integer.parseInt(br.readLine());
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }
}
