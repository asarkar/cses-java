package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.junit.jupiter.params.provider.Arguments;

public class ArgumentsProvider {
    public static Stream<Arguments> getArgs(
            String resource, Function<BufferedReader, List<?>> inF, Function<BufferedReader, Object> outF) {
        try (ZipFile zf = new ZipFile(
                new File(ArgumentsProvider.class.getResource(resource).toURI()))) {
            Map<String, Object[]> args = new HashMap<>();
            Enumeration<? extends ZipEntry> entries = zf.entries();
            while (entries.hasMoreElements()) {
                ZipEntry next = entries.nextElement();
                String[] name = next.getName().split("\\.");
                var vals = args.computeIfAbsent(name[0], k -> new Object[] {null, null});
                try (BufferedReader br =
                        new BufferedReader(new InputStreamReader(zf.getInputStream(next), StandardCharsets.UTF_8))) {
                    if (name[1].equals("in")) {
                        vals[0] = inF.apply(br);
                    } else {
                        vals[1] = outF.apply(br);
                    }
                }
            }
            return args.values().stream().map(v -> {
                List<?> inputs = (List<?>) v[0];
                int n = inputs.size();
                var ret = inputs.toArray(i -> new Object[n + 1]);
                ret[n] = v[1];
                return Arguments.of(ret);
            });
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
