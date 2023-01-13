package hwstreams;

import java.util.Map;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class GitHubProc {
  public static Long getWordCount(Stream<GitHubComment> stream, String word) {
    // added parallel() to follow hw specs
    return stream.parallel().mapToLong(n -> Arrays.stream(Util.getWords(n.body())).filter(f -> f.equals(word)).count()).sum();
  }

  public static Map<String, Long> getPerProjectCount(Stream<GitHubComment> stream) {
    // added parallel() to follow hw spec
    return stream.parallel().collect(Collectors.groupingBy(n -> Util.getProject(n), Collectors.counting()));
  }

  public static Map<String, Long> getAuthorActivity(Stream<GitHubComment> stream) {
    // added parallel() to follow hw spec
    return stream.parallel().collect(Collectors.groupingBy(n -> n.author(), Collectors.counting()));
  }

  public static Stream<GitHubComment> filterCommentsWithUrl(Stream<GitHubComment> comments) {
    // added parallel() to follow hw spec
    Stream<GitHubComment> filteredComments = comments.parallel().filter(n -> n.body().contains("https://") || n.body().contains("http://"));
    return filteredComments;
  }

  public static Map<String, Long> getCommentUrlAuthorCount(Stream<GitHubComment> stream) {
    // added parallel() to follow hw spec
    return filterCommentsWithUrl(stream).parallel().collect(Collectors.groupingBy(n -> n.author(), Collectors.counting()));
  }

  public static Map<String, Double> getAuthorAverageVerbosity(Stream<GitHubComment> stream) {
    // added parallel() to follow hw spec
    return stream.parallel().collect(Collectors.groupingBy(n -> n.author(), Collectors.averagingDouble(n -> Util.getWords(n.body()).length)));
  }

  public static Map<String, Map<String, Long>> getAuthorWordCountPerProject(
      Stream<GitHubComment> stream, String word) {
    // added parallel() to follow hw spec
    return stream.parallel().collect(Collectors.groupingBy(n -> Util.getProject(n), Collectors.groupingBy(n -> n.author(), Collectors.summingLong(n -> Arrays.stream(Util.getWords(n.body())).filter(f -> f.equals(word)).count()))));
  }
}
