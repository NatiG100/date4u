package com.tutego.date4u.interfaces.shell;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class JdbcCommands{
    private final JdbcTemplate jdbcTemplate;
    public JdbcCommands(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @ShellMethod("Display mane length of a given profile by nickname")
    public String manelength(String nickname){
        String sql = "SELECT manelength FROM profile WHERE nickname = ?";
        List<Integer> lengths = jdbcTemplate.queryForList(sql, Integer.class, nickname);
        return lengths.isEmpty()?"Unknown profile for nickname " + nickname
                :lengths.get(0).toString();
    }
}
