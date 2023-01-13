package hwstreams;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class StudentTest {
    Stream<GitHubComment> getTestData() {
        return Stream.of(
                new GitHubComment(
                        "22422999",
                        "989b553963f11e4c33b0668665a988f4e487347b",
                        "https://github.com/zotero/translators/pull/832#discussion_r22422999",
                        "aurimasv",
                        "2015-01-02T21:11:30Z",
                        "`volume.length` always `true` in your regexp (same below)")
        );
    }
    @Test
    public void studentShouldTest() { // TODO: rename test name to something meaningful
        //assertFalse(true); // TODO: implement a non-trivial test
        var testMap1 = GitHubProc.getWordCount(getTestData(), "the");
        assertEquals(0, testMap1); // "the" is not present in the comment. This test case checks an edge case.
        var testMap2 = GitHubProc.getWordCount(getTestData(), "always");
        assertEquals(1, testMap2);
    }
}
