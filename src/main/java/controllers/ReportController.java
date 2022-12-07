package controllers;

import entities.User;
import useCases.Report;

import java.io.IOException;

public class ReportController {

    public void reportController(User mainuser, User reporteduser) throws IOException, ClassNotFoundException {
        Report report = new Report(mainuser, reporteduser);
        report.checkReport();
    }
}