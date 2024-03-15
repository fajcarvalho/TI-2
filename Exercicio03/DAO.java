package service;

import static spark.Spark.*;
import dao.ProdutoDAO;
import model.Produto;
import spark.Request;
import spark.Response;

public class ProdutoService {
    private ProdutoDAO produtoDAO;

    public ProdutoService() {
        this.produtoDAO = new ProdutoDAO();
    }

    public Object insert(Request req, Response res) {
        String descricao = req.queryParams("descricao");
        double preco = Double.parseDouble(req.queryParams("preco"));
        int quantidade = Integer.parseInt(req.queryParams("quantidade"));
        String dataFabricacao = req.queryParams("datafabricacao");
        String dataValidade = req.queryParams("datavalidade");

        Produto produto = new Produto(0, descricao, preco, quantidade, dataFabricacao, dataValidade);
        produtoDAO.insert(produto);
        res.redirect("/produto/list/descricao");
        return null;
    }

    public Object get(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        Produto produto = produtoDAO.get(id);
        if (produto != null) {
            return produto.toString();
        } else {
            res.status(404);
            return "Produto não encontrado";
        }
    }

    public Object getAll(Request req, Response res) {
        String orderby = req.params(":orderby");
        return produtoDAO.get(orderby).toString();
    }

    public Object getToUpdate(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        Produto produto = produtoDAO.get(id);
        if (produto != null) {
            return produto.toString();
        } else {
            res.status(404);
            return "Produto não encontrado";
        }
    }

    public Object update(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        String descricao = req.queryParams("descricao");
        double preco = Double.parseDouble(req.queryParams("preco"));
        int quantidade = Integer.parseInt(req.queryParams("quantidade"));
        String dataFabricacao = req.queryParams("datafabricacao");
        String dataValidade = req.queryParams("datavalidade");

        Produto produto = new Produto(id, descricao, preco, quantidade, dataFabricacao, dataValidade);
        produtoDAO.update(produto);
        res.redirect("/produto/list/descricao");
        return null;
    }

    public Object delete(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        produtoDAO.delete(id);
        res.redirect("/produto/list/descricao");
        return null;
    }
}
