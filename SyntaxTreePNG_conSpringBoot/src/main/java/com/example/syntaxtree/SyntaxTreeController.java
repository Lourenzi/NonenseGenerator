package com.example.syntaxtree;

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.view.mxGraph;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/tree")
public class SyntaxTreeController {

    public static class Token {
        public String content;
        public int headTokenIndex;
    }

    @PostMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<ByteArrayResource> generateTreeImage(@RequestBody List<Token> tokens) throws Exception {
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        Object[] nodes = new Object[tokens.size()];
        try {
            for (int i = 0; i < tokens.size(); i++) {
                nodes[i] = graph.insertVertex(parent, null, tokens.get(i).content, 0, 0, 80, 30);
            }
            for (int i = 0; i < tokens.size(); i++) {
                int head = tokens.get(i).headTokenIndex;
                if (i != head && head >= 0 && head < tokens.size()) {
                    graph.insertEdge(parent, null, "", nodes[head], nodes[i]);
                }
            }
        } finally {
            graph.getModel().endUpdate();
        }

        mxCompactTreeLayout layout = new mxCompactTreeLayout(graph);
        layout.setHorizontal(false);
        layout.execute(parent);

        BufferedImage image = mxCellRenderer.createBufferedImage(graph, null, 1, null, true, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", baos);
        ByteArrayResource resource = new ByteArrayResource(baos.toByteArray());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=tree.png")
                .contentType(MediaType.IMAGE_PNG)
                .contentLength(resource.contentLength())
                .body(resource);
    }
}