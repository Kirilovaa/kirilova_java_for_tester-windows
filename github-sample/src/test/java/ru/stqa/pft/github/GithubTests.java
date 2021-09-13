package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub(" 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");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("Kirilovaa", "kirilova_java_for_tester-windows")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
