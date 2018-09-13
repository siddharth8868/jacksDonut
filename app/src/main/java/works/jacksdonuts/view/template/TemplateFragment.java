package works.jacksdonuts.view.template;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import works.jacksdonuts.R;
import works.jacksdonuts.presenter.home.HomePresenter;
import works.jacksdonuts.presenter.home.HomePresenterImpl;
import works.jacksdonuts.presenter.template.TemplatePresenter;
import works.jacksdonuts.presenter.template.TemplatePresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class TemplateFragment extends Fragment implements TemplateView {

    private TemplateActivity activity;

    private TemplatePresenter presenter;

    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (TemplateActivity) getActivity();
        presenter = new TemplatePresenterImpl();
        presenter.bind(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_template, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.initializeWork();
    }

    @Override
    public void showProgressDialog() {
        activity.showProgressDialog();
    }

    @Override
    public void dismissProgressDialog() {
        activity.showProgressDialog();
    }
}