package ru.edu.pr03;


public class ApprovalRouter {

    // лимит (500 000)
    public static final long FINANCE_LIMIT = 500_000L;

    //  6 вариант
    public static final long URGENT_MODIFIER = 35L;

    // Итоговый порог
    public static final long FAST_TRACK_LIMIT = FINANCE_LIMIT - URGENT_MODIFIER;

    public ApprovalRoute route(long amount, boolean urgent, RiskLevel risk, boolean containsPersonalData) {

        // 1. Приоритет безопасности
        if (containsPersonalData) {
            return ApprovalRoute.SECURITY_REVIEW;
        }

        // 2. Высокий риск требует директора
        if (risk == RiskLevel.HIGH) {
            return ApprovalRoute.DIRECTOR_REVIEW;
        }

        // 3. Финансовый лимит
        if (amount >= FINANCE_LIMIT) {
            return ApprovalRoute.FINANCE_REVIEW;
        }

        // 4. Срочность
        if (urgent && amount < FAST_TRACK_LIMIT) {
            return ApprovalRoute.FAST_TRACK;
        }

        // 5. Во всех остальных случаях
        return ApprovalRoute.STANDARD;
    }
}