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
class P1084Test {
    @ParameterizedTest
    @CsvSource(
            delimiter = '|',
            textBlock =
                    """
        [90, 41, 20, 39, 49, 21, 35, 31, 74, 86] | [14, 24, 24, 7, 82, 85, 82, 4, 60, 95] | 10 | 6
    """)
    @MethodSource
    @Timeout(1)
    void testNumApartments(
            @ConvertWith(IterableConverter.class) List<Integer> desiredSizes,
            @ConvertWith(IterableConverter.class) List<Integer> sizes,
            int k,
            int expected) {
        assertThat(P1084.numApartments(desiredSizes, sizes, k)).isEqualTo(expected);
    }

    static Stream<Arguments> testNumApartments() {
        return getArgs(
                "/1084.zip",
                br -> {
                    List<List<Integer>> in = P1084.readIn(br);
                    return List.of(in.get(0), in.get(1), in.get(2).get(0));
                },
                br -> {
                    try {
                        return Integer.parseInt(br.readLine());
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });
    }
}
