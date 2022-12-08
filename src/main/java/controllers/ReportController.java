package controllers;

import entities.User;
import useCases.Report;

import java.io.IOException;

public class ReportController {

    /**
     *
     * @param mainuser
     * @param reporteduser
     * @throws IOException
     * @throws ClassNotFoundException
     *
     * Calls the report usecase for reporting a friend
     */
    public void reportController(User mainuser, User reporteduser) throws IOException, ClassNotFoundException {
        Report report = new Report(mainuser, reporteduser);
        report.checkReport();
    }
}