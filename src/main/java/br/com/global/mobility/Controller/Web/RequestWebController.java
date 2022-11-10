package br.com.global.mobility.Controller.Web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.global.mobility.Model.Address;
import br.com.global.mobility.Model.Item;
import br.com.global.mobility.Model.Request;
import br.com.global.mobility.Model.RequestItem;
import br.com.global.mobility.Model.State;
import br.com.global.mobility.Service.FreightFactorService;
import br.com.global.mobility.Service.ItemService;
import br.com.global.mobility.Service.RequestItemService;
import br.com.global.mobility.Service.RequestService;
import br.com.global.mobility.Service.StateService;

@Controller
@RequestMapping("/request")
public class RequestWebController {

    @Autowired
    RequestService service;

    @Autowired
    StateService stateService;

    @Autowired
    RequestItemService requestItemService;

    @Autowired
    ItemService itemSerive;

    @Autowired
    FreightFactorService freightFactorService;

    @GetMapping
    public ModelAndView index(@PageableDefault(size = 3) Pageable pageable){

        return
            new ModelAndView("/request/index")
            .addObject("requests", service.listAll(pageable));

    }

    @GetMapping("new")
    public ModelAndView form(){

        Request request = new Request();
        
        List<State> states = stateService.listAllStates();
        List<RequestItem> requestItens = requestItemService.listAll().get();
        List<Item> itens = itemSerive.listAll().get();

        ModelAndView mav = new ModelAndView("/request/form");
        mav.addObject("request", request);
        mav.addObject("states", states);
        mav.addObject("requestItens", requestItens);
        mav.addObject("itens", itens);

        return mav;
    }

    @PostMapping
    public String create(@Valid Request request, BindingResult binding,  RedirectAttributes redirect){

        if (binding.hasErrors()){
            return "/request/form";
        } 
        String message = (request.getId() == null)?"Pedido cadastrada com sucesso":"Pedido alterada com sucesso";

        System.out.println(request.toString());

        if(request.getId() != null){

            Request requestOld = service.findById(request.getId()).get();

            if(!request.getAddress().getStreet().equals(requestOld.getAddress().getStreet())){
                requestOld.getAddress().setStreet(request.getAddress().getStreet());
            }
    
            if(request.getAddress().getStreetNumber() != requestOld.getAddress().getStreetNumber()){
                requestOld.getAddress().setStreetNumber(request.getAddress().getStreetNumber());
            }
    
            if(request.getAddress().getState().getId() != requestOld.getAddress().getState().getId()){
                State state = stateService.findById(request.getAddress().getState().getId()).get();
                Address address = requestOld.getAddress();
                address.addState(state);
                request.addAddress(address);
            }

            if(request.getRequestItemList().get(0).getValue()  != requestOld.getRequestItemList().get(0).getValue()){
                // System.out.println("Entrou no if");
                requestOld.getRequestItemList().get(0).setValue(request.getRequestItemList().get(0).getValue());
            }

            if(request.getRequestItemList().get(0).getItem().getId() != requestOld.getRequestItemList().get(0).getItem().getId()){
                Integer id = request.getRequestItemList().get(0).getItem().getId();
                Item item = itemSerive.findById(id).get();
                RequestItem requestItem = requestOld.getRequestItemList().get(0);
                requestItem.setItem(item);;
            }
    
            requestOld.freightCalculation();

            service.save(requestOld);
                
        }else{
            service.save(request);
        }

        redirect.addFlashAttribute("message", message);
        return "redirect:/request";
        
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirect){
        service.deleteById(id);
        redirect.addFlashAttribute("message", "Pedido apagado com sucesso");
        return "redirect:/request";
    }

    @GetMapping("{id}")
    public ModelAndView edit(@PathVariable Integer id){

        Request request = service.findById(id).get();
        List<State> states = stateService.listAllStates();
        List<RequestItem> requestItens = requestItemService.listAll().get();
        List<Item> itens = itemSerive.listAll().get();

        ModelAndView mav = new ModelAndView("/request/form");
        mav.addObject("request", request);
        mav.addObject("states", states);
        mav.addObject("requestItens", requestItens);
        mav.addObject("itens", itens);

        return mav;
            
    }
    
}
