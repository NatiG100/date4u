package com.tutego.date4u.core;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FileSystemTest {
    @Test
    @DisplayName("Free disk space has to be positive")
    void free_disk_space_has_to_be_positive() {
        // given
        FileSystem fs = new FileSystem();

        // when
        long actual = fs.getFreeDiskSpace();

        // then
        // assertTrue(actual>0, "Free disk space was not > 0");
        assertThat(actual).isGreaterThan(0);
    }

    @Test
    void store_and_load_successful() {
        FileSystem fileSystem = new FileSystem();
        fileSystem.store("test.txt", "Hello World".getBytes());
        byte[] actual = fileSystem.load("test.txt");
        assertThat(actual).containsExactly("Hello World".getBytes());
    }

    @Test
    void load_unknown_file_throws_exception() {
        FileSystem fileSystem = new FileSystem();
        assertThatThrownBy(() -> {
            fileSystem.load(UUID.randomUUID().toString());
        }).isInstanceOf(UncheckedIOException.class);
    }

    @Test
    void exists_works_correctly() {
        FileSystem fileSystem = new FileSystem();
        boolean actual = fileSystem.exists(Path.of(UUID.randomUUID().toString()));
        assertThat(actual).isEqualTo(false);
        fileSystem.store("test-exists.txt", "it exists".getBytes());
        actual = fileSystem.exists(Paths.get(System.getProperty("user.home")).resolve("fs/test-exists.txt"));
        assertThat(actual).isEqualTo(true);
    }

    @Test
    void load_arbitrary_file() throws IOException {
        FileSystem fileSystem = new FileSystem();
        assertThatThrownBy(() -> {
            fileSystem.load("../../../../../../../../../../Windows/notepad.exe");
        }).isInstanceOf(SecurityException.class)
                .hasMessageContaining("Access to")
                .hasMessageContaining("denied");
    }
}
