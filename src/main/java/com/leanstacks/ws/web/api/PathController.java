package com.leanstacks.ws.web.api;

import com.leanstacks.ws.model.Path;
import com.leanstacks.ws.service.PathService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paths")
public class PathController {

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(PathController.class);

    @Autowired
    private transient PathService pathService;

    @GetMapping
    public List<Path> getPaths() {
        logger.info("> getPaths");

        final List<Path> paths = pathService.findAll();

        logger.info("< getPaths");
        return paths;
    }

    @GetMapping("/{id}")
    public Path getPathsById(@PathVariable final Long id) {
        logger.info("> getPaths");

        Path path = pathService.findById(id);

        logger.info("< getPaths");
        return path;
    }
}
